package com.example.datasource.local.source.entity.product

import androidx.room.Entity

@Entity(primaryKeys = ["productId", "tagId"])
data class ProductTagCrossRef(
    val productId: Int,
    val tagId: Int,
)