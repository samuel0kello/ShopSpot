package com.samuelokello.domain.repository

import com.samuelokello.core.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
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
