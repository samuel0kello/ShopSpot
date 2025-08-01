package com.example.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.local.entity.DimensionsEntity
import com.example.datasource.local.entity.MetaEntity
import com.example.datasource.local.entity.ReviewEntity
import com.example.datasource.local.entity.TagEntity
import com.example.datasource.local.entity.cart.CartEntity
import com.example.datasource.local.entity.cart.CartProductEntity
import com.example.datasource.local.entity.category.CategoryEntity
import com.example.datasource.local.entity.product.ProductEntity
import com.example.datasource.local.entity.product.ProductImageEntity
import com.example.datasource.local.entity.product.ProductTagCrossRef
import com.example.datasource.local.entity.user.UserEntity

@Database(
    entities = [
        CartEntity::class,
        CartProductEntity::class,
        CategoryEntity::class,
        DimensionsEntity::class,
        MetaEntity::class,
        ProductEntity::class,
        ProductImageEntity::class,
        ProductTagCrossRef::class,
        ReviewEntity::class,
        TagEntity::class,
        UserEntity::class,
    ],
    version = 1,
    autoMigrations = [],
)
abstract class ShopSpotDB : RoomDatabase()