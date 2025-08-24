package com.samuelokello.datasource.local.entity.product

import androidx.room.Entity

@Entity(primaryKeys = ["productId", "tagId"])
data class ProductTagCrossRef(
    val productId: Int,
    val tagId: Int,
)