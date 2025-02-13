package com.samuelokello.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samuelokello.local.converters.CartProductConverter
import com.samuelokello.local.dao.ProductDao
import com.samuelokello.local.dao.UserCartDao
import com.samuelokello.local.entity.ProductEntity
import com.samuelokello.local.entity.User
import com.samuelokello.local.entity.UserCartEntity

@Database(
    entities = [
        ProductEntity::class,
        User::class,
        UserCartEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(CartProductConverter::class)
abstract class ShopSpotDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun cartDao(): UserCartDao
}
