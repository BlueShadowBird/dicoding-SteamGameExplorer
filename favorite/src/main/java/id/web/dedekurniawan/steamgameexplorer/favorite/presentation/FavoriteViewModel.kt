package id.web.dedekurniawan.steamgameexplorer.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.web.dedekurniawan.steamgameexplorer.core.domain.usecase.SteamGameUseCase

class FavoriteViewModel(private val useCase: SteamGameUseCase): ViewModel() {
    fun getAllFavorite() = useCase.getAllFavorite().asLiveData()
}