package id.web.dedekurniawan.steamgameexplorer

import id.web.dedekurniawan.steamgameexplorer.core.data.di.networkModule
import id.web.dedekurniawan.steamgameexplorer.core.data.di.repositoryModule
import id.web.dedekurniawan.steamgameexplorer.di.adapterModule
import id.web.dedekurniawan.steamgameexplorer.di.useCaseModule
import id.web.dedekurniawan.steamgameexplorer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SteamGameApplication: com.google.android.play.core.splitcompat.SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SteamGameApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    adapterModule
                )
            )
        }

    }
}