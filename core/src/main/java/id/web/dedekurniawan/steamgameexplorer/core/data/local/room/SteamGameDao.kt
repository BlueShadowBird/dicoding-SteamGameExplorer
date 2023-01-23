package id.web.dedekurniawan.steamgameexplorer.core.data.local.room

import androidx.room.*
import id.web.dedekurniawan.steamgameexplorer.core.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SteamGameDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE app_id = :appId")
    fun getFavoriteGame(appId: String): Flow<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGames(games: List<FavoriteEntity>)

    @Query("DELETE FROM favorite")
    fun deleteFavorites()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteGame(game: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE app_id = :appId")
    fun deleteFavoriteGame(appId: String)

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE app_id = :appId)")
    fun isFavorited(appId: String): Flow<Boolean>
}