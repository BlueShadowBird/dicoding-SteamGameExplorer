package id.web.dedekurniawan.steamgameexplorer.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.web.dedekurniawan.steamgameexplorer.core.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class SteamGameDatabase: RoomDatabase() {
     abstract fun getDao(): SteamGameDao
}