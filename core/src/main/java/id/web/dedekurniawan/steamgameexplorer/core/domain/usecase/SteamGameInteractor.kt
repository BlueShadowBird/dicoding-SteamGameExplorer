package id.web.dedekurniawan.steamgameexplorer.core.domain.usecase

import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import id.web.dedekurniawan.steamgameexplorer.core.domain.repository.ISteamGameRepository
import kotlinx.coroutines.flow.Flow
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.Result

class SteamGameInteractor(private val repository: ISteamGameRepository): SteamGameUseCase {
    override suspend fun searchGame(keyWord: String): Flow<Result<Game>> = repository.searchGame(keyWord)

    override fun saveGameToFavorite(game: Game) {
        repository.saveGameToFavorite(game)
    }

    override fun deleteFavoriteGame(appId: String) {
        repository.deleteFavoriteGame(appId)
    }

    override fun getAllFavorite(): Flow<List<Game>> = repository.getAllFavorite()

    override fun isFavorite(appId: String): Flow<Boolean> = repository.isFavorite(appId)
}