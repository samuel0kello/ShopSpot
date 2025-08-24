package com.samuelokello.remote.sources.product

import com.samuelokello.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRemoteSource {
    fun getProducts(): Flow<List<Product>>

    fun searchProductsWithFilters(
        query: String,
        minPrice: Double?,
        maxPrice: Double?,
        category: String?,
        minCount: Int?,
        minRating: Double?,
    ): Flow<List<Product>>

    fun getProductById(id: Int): Product
}

class ProductRemoteSourceImpl : ProductRemoteSource {
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