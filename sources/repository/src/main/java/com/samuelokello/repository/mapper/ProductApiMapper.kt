package com.samuelokello.repository.mapper

import com.samuelokello.core.model.Product
import com.samuelokello.local.entity.ProductEntity
import com.samuelokello.network.product.dto.ProductDto

class ProductApiMapper {
    fun toEntity(apiModel: ProductDto): ProductEntity =
        ProductEntity(
            id = apiModel.id,
            title = apiModel.title,
            price = apiModel.price,
            description = apiModel.description,
            category = apiModel.category,
            image = apiModel.image,
            rate = apiModel.rating?.rate ?: 0.00,
            count = apiModel.rating?.count ?: 0,
        )

    fun toDomain(apiModel: ProductDto): Product =
        Product(
            id = apiModel.id,
            title = apiModel.title,
            price = apiModel.price,
            description = apiModel.description,
            category = apiModel.category,
            image = apiModel.image,
            rating = apiModel.rating?.rate ?: 0.00,
            count = apiModel.rating?.count ?: 0,
        )
}
