package id.web.dedekurniawan.steamgameexplorer.core.data.di

import androidx.room.Room
import id.web.dedekurniawan.steamgameexplorer.core.BuildConfig
import id.web.dedekurniawan.steamgameexplorer.core.data.local.room.SteamGameDatabase
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.SteamGameRepository
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.retrofit.ApiService
import id.web.dedekurniawan.steamgameexplorer.core.domain.repository.ISteamGameRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        val certificatePinner = CertificatePinner.Builder()
            .add("steamcommunity.com", BuildConfig.STEAMCOMMUNITY_CERTIFICATE)
            .add("store.steampowered.com", BuildConfig.STEAMSTORE_CERTIFICATE)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(
                if(BuildConfig.DEBUG)
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            ).certificatePinner(certificatePinner)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://store.steampowered.com/")
            .client(get())
            .build().create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { get<SteamGameDatabase>().getDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("SteamGameExplorer".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SteamGameDatabase::class.java, "Favorite.db").fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    single<ISteamGameRepository> { SteamGameRepository(get(), get()) }
}