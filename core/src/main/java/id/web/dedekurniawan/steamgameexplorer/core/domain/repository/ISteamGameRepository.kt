package id.web.dedekurniawan.steamgameexplorer.core.domain.repository

import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.Result

interface ISteamGameRepository {
    suspend fun searchGame(keyWord: String): Flow<Result<Game>>
    fun saveGameToFavorite(game: Game)
    fun deleteFavoriteGame(appid: String)
    fun getAllFavorite(): Flow<List<Game>>
    fun isFavorite(appId: String): Flow<Boolean>
}