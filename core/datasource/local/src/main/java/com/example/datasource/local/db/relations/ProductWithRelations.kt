package com.example.datasource.local.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.datasource.local.entity.DimensionsEntity
import com.example.datasource.local.entity.MetaEntity
import com.example.datasource.local.entity.ReviewEntity
import com.example.datasource.local.entity.product.ProductEntity
import com.example.datasource.local.entity.product.ProductImageEntity

data class ProductWithRelations(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val images: List<ProductImageEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val reviews: List<ReviewEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val dimensions: DimensionsEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val meta: MetaEntity?,
)