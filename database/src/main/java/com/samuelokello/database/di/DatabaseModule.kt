package com.samuelokello.database.di

import android.content.Context
import androidx.room.Room
import com.samuelokello.database.ShopSpotDatabase
import com.samuelokello.database.dao.ProductDao
import com.samuelokello.database.dao.UserCartDao
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
    fun provideDatabase(context: Context): ShopSpotDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ShopSpotDatabase::class.java,
            "shop_spot_db"
        ).build()
    }

    @Provides
    fun provideProductDao(database: ShopSpotDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideUserCartDao(database: ShopSpotDatabase): UserCartDao {
        return database.cartDao()
    }
}
