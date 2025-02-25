package com.samuelokello.domain.usecases

import com.samuelokello.core.model.Product
import com.samuelokello.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        operator fun invoke(): Flow<List<Product>> = productRepository.getProducts()
    }

class SearchProductsWithFiltersUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        operator fun invoke(
            query: String,
            minPrice: Double?,
            maxPrice: Double?,
            category: String?,
            minCount: Int?,
            minRating: Double?,
        ): Flow<List<Product>> =
            productRepository.searchProductsWithFilters(
                query,
                minPrice,
                maxPrice,
                category,
                minCount,
                minRating,
            )
    }

class GetProductByIdUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        operator fun invoke(id: Int): Product = productRepository.getProductById(id)
    }
