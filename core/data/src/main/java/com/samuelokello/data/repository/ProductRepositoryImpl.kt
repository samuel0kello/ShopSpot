package com.samuelokello.data.repository

import com.samuelokello.core.domain.model.Product
import com.samuelokello.core.domain.repository.ProductRepository
import com.samuelokello.remote.sources.product.ProductRemoteSource
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val remote: ProductRemoteSource,
) : ProductRepository {
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