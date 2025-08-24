package com.samuelokello.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samuelokello.datasource.local.entity.DimensionsEntity
import com.samuelokello.datasource.local.entity.MetaEntity
import com.samuelokello.datasource.local.entity.ReviewEntity
import com.samuelokello.datasource.local.entity.TagEntity
import com.samuelokello.datasource.local.entity.cart.CartEntity
import com.samuelokello.datasource.local.entity.cart.CartProductEntity
import com.samuelokello.datasource.local.entity.category.CategoryEntity
import com.samuelokello.datasource.local.entity.product.ProductEntity
import com.samuelokello.datasource.local.entity.product.ProductImageEntity
import com.samuelokello.datasource.local.entity.product.ProductTagCrossRef
import com.samuelokello.datasource.local.entity.user.UserEntity

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