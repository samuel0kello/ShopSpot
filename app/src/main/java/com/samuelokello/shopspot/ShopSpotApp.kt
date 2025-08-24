package com.samuelokello.shopspot

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.samuelokello.core.presentation.ui.di.uiModule
import com.samuelokello.data.repository.di.dataModule
import com.samuelokello.datasource.local.di.localDataSourceModule
import com.samuelokello.remote.di.remoteDataSourceModule
import com.samuelokello.shopspot.di.applicationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class ShopSpotApp :
    Application(),
    ImageLoaderFactory {
    val applicationScope: CoroutineScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    override fun newImageLoader(): ImageLoader =
        ImageLoader(this)
            .newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache
                    .Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }.diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache
                    .Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }.logger(DebugLogger())
            .build()

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ShopSpotApp)

            modules(
                applicationModule,
                dataModule,
                localDataSourceModule,
                remoteDataSourceModule,
                uiModule,
            )
        }
    }
}
