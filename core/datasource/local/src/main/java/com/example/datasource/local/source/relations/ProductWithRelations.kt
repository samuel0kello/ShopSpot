package com.example.datasource.local.source.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.datasource.local.source.entity.DimensionsEntity
import com.example.datasource.local.source.entity.MetaEntity
import com.example.datasource.local.source.entity.ReviewEntity
import com.example.datasource.local.source.entity.product.ProductEntity
import com.example.datasource.local.source.entity.product.ProductImageEntity

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