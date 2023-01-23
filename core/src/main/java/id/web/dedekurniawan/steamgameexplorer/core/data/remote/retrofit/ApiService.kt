package id.web.dedekurniawan.steamgameexplorer.core.data.remote.retrofit

import id.web.dedekurniawan.steamgameexplorer.core.data.remote.response.GameDataItem
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.response.GameResponse
import retrofit2.http.*

interface ApiService {
    @GET("https://steamcommunity.com/actions/SearchApps/{keyWord}")
    suspend fun searchGame(
        @Path("keyWord") keyWord: String
    ): List<GameDataItem>

    @GET("https://store.steampowered.com/api/appdetails")
    suspend fun retrieveGame(
        @Query("appids") appId: String
    ): Map<String, GameResponse>?
}
