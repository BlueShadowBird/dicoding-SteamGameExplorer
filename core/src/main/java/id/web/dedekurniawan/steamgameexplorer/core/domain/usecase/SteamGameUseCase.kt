package id.web.dedekurniawan.steamgameexplorer.core.domain.usecase

import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.Result

interface SteamGameUseCase {
    suspend fun searchGame(keyWord: String): Flow<Result<Game>>
    fun saveGameToFavorite(game: Game)
    fun deleteFavoriteGame(appId: String)
    fun getAllFavorite(): Flow<List<Game>>
    fun isFavorite(appId: String): Flow<Boolean>
}