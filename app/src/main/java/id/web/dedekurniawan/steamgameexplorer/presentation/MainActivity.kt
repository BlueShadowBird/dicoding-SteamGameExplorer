package id.web.dedekurniawan.steamgameexplorer.presentation

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import com.google.android.play.core.splitinstall.SplitInstallManager
import id.web.dedekurniawan.steamgameexplorer.R
import id.web.dedekurniawan.steamgameexplorer.core.adapter.GameAdapter
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.Result
import id.web.dedekurniawan.steamgameexplorer.databinding.ActivityMainBinding
import id.web.dedekurniawan.steamgameexplorer.presentation.view.GameActivity
import id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel.SearchViewModel
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import id.web.dedekurniawan.steamgameexplorer.core.utils.alert
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter: GameAdapter by inject()
    private val viewModel: SearchViewModel by viewModel()

    private lateinit var moduleFavorite: String

    private lateinit var splitInstallManager: SplitInstallManager
    private var isFavoriteModuleInstalled = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moduleFavorite = getString(R.string.favorite_module)

        splitInstallManager = SplitInstallManagerFactory.create(this)
        isFavoriteModuleInstalled = splitInstallManager.installedModules.contains(moduleFavorite)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.onClickListener = { game ->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_IS_FAVORITE_MODULE_INSTALLED, isFavoriteModuleInstalled)
            intent.putExtra(GameActivity.EXTRA_GAME, game)
            startActivity(intent)
        }
        binding.rvSearch.adapter = adapter

        viewModel.searchResult.observe(this){ result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    val gameList = result.data
                    binding.progressBar.visibility = View.GONE
                   if (gameList?.size == 0){
                       alert(binding.root, "No Game Found")
                   }else {
                       adapter.submitList(gameList)
                   }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    alert(binding.root, result.message.toString())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager = getSystemService<SearchManager>()
        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                viewModel.searchGame(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_favorite -> installModuleFavorite()
        }
        return true
    }

    private fun installModuleFavorite(){
        if(isFavoriteModuleInstalled){
            moveToFavorite()
        }else{
            val mAlertDialog = AlertDialog.Builder(this)
            mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
            mAlertDialog.setTitle("Install Favorite!") //set alertdialog title
            mAlertDialog.setMessage("Dou want to download and install favorite feature") //set alertdialog message
            mAlertDialog.setPositiveButton("Yes") { _, _ ->
                val request = SplitInstallRequest.newBuilder()
                    .addModule(moduleFavorite)
                    .build()
                splitInstallManager.startInstall(request)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
                        isFavoriteModuleInstalled = true
                        moveToFavorite()
                    }
                    .addOnFailureListener {
                        isFavoriteModuleInstalled = false
                        Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
                    }
            }
            mAlertDialog.setNegativeButton("No") { _, _ ->
            }
            mAlertDialog.show()
        }
    }

    private fun moveToFavorite(){
        val uri = Uri.parse("steamexplorer://favorite")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}