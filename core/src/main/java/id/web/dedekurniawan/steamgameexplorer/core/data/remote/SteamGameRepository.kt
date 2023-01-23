package id.web.dedekurniawan.steamgameexplorer.core.data.remote

import id.web.dedekurniawan.steamgameexplorer.core.data.remote.retrofit.ApiService
import id.web.dedekurniawan.steamgameexplorer.core.domain.model.Game
import id.web.dedekurniawan.steamgameexplorer.core.domain.repository.ISteamGameRepository
import id.web.dedekurniawan.steamgameexplorer.core.utils.toDomainModel
import id.web.dedekurniawan.steamgameexplorer.core.data.local.room.SteamGameDao
import id.web.dedekurniawan.steamgameexplorer.core.utils.toFavoriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SteamGameRepository(
    private val apiService: ApiService,
    private val dao: SteamGameDao

): ISteamGameRepository{

    override suspend fun searchGame(keyWord: String): Flow<Result<Game>> = flow {
        emit(Result.Loading())
        try {
            val gameDataItemList = apiService.searchGame(keyWord)
            gameDataItemList.forEach { gameDataItem ->
                try {
                    val appId = gameDataItem.appId
                    val favorite = dao.getFavoriteGame(appId).first()
                    val game = if(favorite == null){
                        val mapResponse = apiService.retrieveGame(appId)
                        mapResponse?.get(appId)?.data?.toDomainModel()

                    }else{
                        favorite.toDomainModel()
                    }
                    if(game==null)emit(Result.Error("No data"))
                    else emit(Result.Data(game))

                }catch (e: java.lang.Exception){
                    e.printStackTrace()
                    e.message?.let { message ->
                        emit(Result.Error(StringBuilder("Failed Retrieve Game: ").append(message).toString()))
                    }
                }
            }
            emit(Result.Done())
        }catch (e: java.lang.Exception){
            e.printStackTrace()
            e.message?.let { message ->
                emit(Result.Error(StringBuilder("Failed search Game: ").append(message).toString()))
                emit(Result.Done())
            }
        }
    }

    override fun saveGameToFavorite(game: Game){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertFavoriteGame(game.toFavoriteEntity())
        }
    }

    override fun deleteFavoriteGame(appid: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteFavoriteGame(appid)
        }
    }

    override fun getAllFavorite(): Flow<List<Game>> = dao.getAllFavorites().map { favoriteEntityList ->
        favoriteEntityList.map { it.toDomainModel() }
    }

    override fun isFavorite(appId: String): Flow<Boolean> = dao.isFavorited(appId)
}