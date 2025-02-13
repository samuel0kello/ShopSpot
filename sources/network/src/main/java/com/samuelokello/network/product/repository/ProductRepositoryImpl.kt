package com.samuelokello.network.product.repository

import com.samuelokello.core.model.Product
import com.samuelokello.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl : ProductRepository {
    override fun getProducts(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun searchProductsWithFilters(
        query: String,
        minPrice: Double?,
        maxPrice: Double?,
        category: String?,
        minCount: Int?,
        minRating: Double?,
    ): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getProductById(id: Int): Product {
        TODO("Not yet implemented")
    }
}
