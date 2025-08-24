package com.samuelokello.datasource.local.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.samuelokello.datasource.local.entity.DimensionsEntity
import com.samuelokello.datasource.local.entity.MetaEntity
import com.samuelokello.datasource.local.entity.ReviewEntity
import com.samuelokello.datasource.local.entity.product.ProductEntity
import com.samuelokello.datasource.local.entity.product.ProductImageEntity

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