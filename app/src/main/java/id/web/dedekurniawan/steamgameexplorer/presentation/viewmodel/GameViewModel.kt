package id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel

import androidx.lifecycle.*
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import id.web.dedekurniawan.steamgameexplorer.core.domain.usecase.SteamGameUseCase
import kotlinx.coroutines.launch

class GameViewModel(private val useCase: SteamGameUseCase): ViewModel() {
    private val _isFavoriteResult = MediatorLiveData<Boolean>()
    val isFavoriteResult: LiveData<Boolean> = _isFavoriteResult

    fun listenFavorite(appId: String){
        _isFavoriteResult.addSource(useCase.isFavorite(appId).asLiveData()){
            _isFavoriteResult.value = it
        }
    }

    fun saveGameToFavorite(game: Game) {
        viewModelScope.launch { useCase.saveGameToFavorite(game) }
    }

    fun deleteFavoriteGame(game: Game) {
        viewModelScope.launch { useCase.deleteFavoriteGame(game.appId) }
    }
}