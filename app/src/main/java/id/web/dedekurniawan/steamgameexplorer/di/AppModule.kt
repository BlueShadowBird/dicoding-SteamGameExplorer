package id.web.dedekurniawan.steamgameexplorer.di

import id.web.dedekurniawan.steamgameexplorer.core.adapter.GameAdapter
import id.web.dedekurniawan.steamgameexplorer.core.domain.usecase.SteamGameInteractor
import id.web.dedekurniawan.steamgameexplorer.core.domain.usecase.SteamGameUseCase
import id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel.GameViewModel
import id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SteamGameUseCase> { SteamGameInteractor(get()) }
}

val adapterModule = module {
    factory { GameAdapter() }
}

val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { GameViewModel(get()) }
}