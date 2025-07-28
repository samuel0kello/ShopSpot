package com.example.datasource.local.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datasource.local.source.entity.DimensionsEntity
import com.example.datasource.local.source.entity.MetaEntity
import com.example.datasource.local.source.entity.ReviewEntity
import com.example.datasource.local.source.entity.TagEntity
import com.example.datasource.local.source.entity.cart.CartEntity
import com.example.datasource.local.source.entity.cart.CartProductEntity
import com.example.datasource.local.source.entity.category.CategoryEntity
import com.example.datasource.local.source.entity.product.ProductEntity
import com.example.datasource.local.source.entity.product.ProductImageEntity
import com.example.datasource.local.source.entity.product.ProductTagCrossRef
import com.example.datasource.local.source.entity.user.UserEntity

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
        UserEntity::class
   ],
    version = 1,
    autoMigrations = [],
)
abstract class ShopSpotDB : RoomDatabase()