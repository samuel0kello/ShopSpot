package com.samuelokello.repository.mapper

import com.samuelokello.core.model.Product
import com.samuelokello.local.entity.ProductEntity

class ProductEntityMapper {
    fun toDomain(entity: ProductEntity): Product =
        Product(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            description = entity.description,
            category = entity.category,
            image = entity.image,
            rating = entity.rate,
            count = entity.count,
        )

    fun toEntity(domain: Product): ProductEntity =
        ProductEntity(
            id = domain.id,
            title = domain.title,
            price = domain.price,
            description = domain.description,
            category = domain.category,
            image = domain.image,
            rate = domain.rating,
            count = domain.count,
        )
}
