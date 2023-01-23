package id.web.dedekurniawan.steamgameexplorer.favorite.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.web.dedekurniawan.steamgameexplorer.core.adapter.GameAdapter
import id.web.dedekurniawan.steamgameexplorer.favorite.databinding.ActivityFavoriteBinding
import id.web.dedekurniawan.steamgameexplorer.favorite.di.favoriteModule
import id.web.dedekurniawan.steamgameexplorer.presentation.view.GameActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    private val adapter: GameAdapter by inject()
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        binding.rvFavorite.adapter = adapter
        adapter.onClickListener = { game ->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(GameActivity.EXTRA_IS_FAVORITE_MODULE_INSTALLED, true)
            intent.putExtra(GameActivity.EXTRA_GAME, game)
            startActivity(intent)
        }

        viewModel.getAllFavorite().observe(this){
            adapter.submitList(it)
        }

        setContentView(binding.root)
    }
}