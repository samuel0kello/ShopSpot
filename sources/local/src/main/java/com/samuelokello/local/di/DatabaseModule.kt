package com.samuelokello.local.di

import android.content.Context
import androidx.room.Room
import com.samuelokello.local.ShopSpotDatabase
import com.samuelokello.local.dao.ProductDao
import com.samuelokello.local.dao.UserCartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): ShopSpotDatabase =
        Room
            .databaseBuilder(
                context.applicationContext,
                ShopSpotDatabase::class.java,
                "shop_spot_db",
            ).build()

    @Provides
    fun provideProductDao(database: ShopSpotDatabase): ProductDao = database.productDao()

    @Provides
    fun providesCartDao(database: ShopSpotDatabase): UserCartDao = database.cartDao()

    @Provides
    fun provideUserCartDao(database: ShopSpotDatabase): UserCartDao = database.cartDao()
}
