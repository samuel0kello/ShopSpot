package com.example.datasource.local.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.local.source.entity.ProductEntity
import com.example.datasource.local.source.entity.UserCartEntity

@Database(
    entities = [ProductEntity::class, UserCartEntity::class],
    version = 1,
    autoMigrations = [],
)
abstract class ShopSpotDB : RoomDatabase()