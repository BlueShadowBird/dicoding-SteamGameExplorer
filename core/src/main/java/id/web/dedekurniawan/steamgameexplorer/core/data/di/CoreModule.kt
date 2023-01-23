package id.web.dedekurniawan.steamgameexplorer.core.data.di

import androidx.room.Room
import id.web.dedekurniawan.steamgameexplorer.core.data.local.room.SteamGameDatabase
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.SteamGameRepository
import id.web.dedekurniawan.steamgameexplorer.core.data.remote.retrofit.ApiService
import id.web.dedekurniawan.steamgameexplorer.core.domain.repository.ISteamGameRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
        Room.databaseBuilder(
            androidContext(),
            SteamGameDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
    single<ISteamGameRepository> { SteamGameRepository(get(), get()) }
}