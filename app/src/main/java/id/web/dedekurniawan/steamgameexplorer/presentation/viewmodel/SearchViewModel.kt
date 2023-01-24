package id.web.dedekurniawan.steamgameexplorer.presentation.viewmodel

import androidx.lifecycle.*
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.Result
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import id.web.dedekurniawan.steamgameexplorer.core.domain.usecase.SteamGameUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase: SteamGameUseCase): ViewModel() {
    private val _searchResult = MediatorLiveData<Result<List<Game>>>()
    val searchResult: LiveData<Result<List<Game>>> = _searchResult

    fun searchGame(query: String) {
        viewModelScope.launch {
            _searchResult.addSource(useCase.searchGame(query).asLiveData()){ _searchResult.value = it }
        }
    }
}