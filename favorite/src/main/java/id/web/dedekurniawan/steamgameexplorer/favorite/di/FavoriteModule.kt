package id.web.dedekurniawan.steamgameexplorer.favorite.di

import id.web.dedekurniawan.steamgameexplorer.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}