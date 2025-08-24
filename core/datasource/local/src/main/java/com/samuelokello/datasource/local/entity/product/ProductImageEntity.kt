package com.samuelokello.datasource.local.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_images")
data class ProductImageEntity(
    @PrimaryKey(autoGenerate = true) val imageId: Int = 0,
    val productId: Int,
    val imageUrl: String,
)