package com.samuelokello.shopspot

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.samuelokello.shopspot.data.DefaultAppContainer
import com.samuelokello.shopspot.data.ShopSpotContainer

class ShopSpotApplication :
    Application(),
    ImageLoaderFactory {
    lateinit var container: ShopSpotContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
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
}