package com.samuelokello.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samuelokello.database.converters.CartProductConverter
import com.samuelokello.database.dao.ProductDao
import com.samuelokello.database.dao.UserCartDao
import com.samuelokello.database.entity.ProductEntity
import com.samuelokello.database.entity.User
import com.samuelokello.database.entity.UserCartEntity

@Database(
    entities = [ProductEntity::class,
    User::class,
    UserCartEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(CartProductConverter::class)
abstract class ShopSpotDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): UserCartDao
}
