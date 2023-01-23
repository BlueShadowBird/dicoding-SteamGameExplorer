package id.web.dedekurniawan.steamgameexplorer.presentation.view

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.web.dedekurniawan.steamgameexplorer.R
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import id.web.dedekurniawan.steamgameexplorer.core.utils.numberFormat
import id.web.dedekurniawan.steamgameexplorer.core.utils.toHTML
import id.web.dedekurniawan.steamgameexplorer.databinding.ActivityGameBinding
import id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.getBooleanExtra(EXTRA_IS_FAVORITE_MODULE_INSTALLED, false))
            binding.favorite.visibility = View.VISIBLE

        val game = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_GAME, Game::class.java)
        }else{
            intent.getParcelableExtra(EXTRA_GAME) as Game?
        }
        bindGame(game!!)

        viewModel.isFavoriteResult.observe(this){
            if(it)binding.favorite.setImageResource(R.drawable.ic_favorited)
            else binding.favorite.setImageResource(R.drawable.ic_favorite)

            game.favorited = it
        }
        viewModel.listenFavorite(game.appId)
    }

    private fun bindGame(game: Game){
        with(binding) {
            supportActionBar?.title = game.name
            Glide.with(this@GameActivity)
                .load(game.imageUrl)
                .into(imageLogo)

            name.text = game.name

            if(game.initialPrice != null){
                if(game.initialPrice != game.finalPrice){
                    finalPrice.visibility = View.VISIBLE
                    price.paintFlags = price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                    val finalPriceFloat = game.finalPrice?.toFloat()?.div(100)
                    finalPrice.text = StringBuilder("Now ").append(game.currencyPrice.toString()).append(finalPriceFloat?.let { numberFormat(it) })
                }
                val priceFloat = game.initialPrice?.toFloat()?.div(100)
                price.text = StringBuilder(game.currencyPrice.toString()).append(numberFormat(priceFloat!!)) //safe, in if null validation
            }

            description.text = game.shortDescription?.toHTML()
            supportedLanguages.text = game.supportedLanguages?.toHTML()
            platforms.text = game.platforms.joinToString(separator = ", ")
            releaseDate.text = game.releaseDate
            minimumRequirements.text = game.minimumRequirements?.toHTML()
            recommendedRequirements.text = game.recommendedRequirements?.toHTML()

            favorite.setOnClickListener {
                if(game.favorited){
                    viewModel.deleteFavoriteGame(game)
                }else{
                   viewModel.saveGameToFavorite(game)
                }
            }
        }
    }

    companion object{
        const val EXTRA_IS_FAVORITE_MODULE_INSTALLED = "isFavoriteModuleInstalled"
        const val EXTRA_GAME = "game"
    }
}