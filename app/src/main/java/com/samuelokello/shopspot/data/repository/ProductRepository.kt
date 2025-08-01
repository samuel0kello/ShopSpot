package com.samuelokello.shopspot.data.repository

import com.samuelokello.shopspot.data.local.product.ProductDao
import com.samuelokello.shopspot.data.mapper.ProductApiMapper
import com.samuelokello.shopspot.data.mapper.ProductEntityMapper
import com.samuelokello.shopspot.data.network.product.ProductApiService
import com.samuelokello.shopspot.domain.Product
import com.samuelokello.shopspot.domain.mappers.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

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

class ProductRepositoryImpl(
    private val productApiService: ProductApiService,
    private val productDao: ProductDao,
    private val productApiMapper: ProductApiMapper,
    private val productEntityMapper: ProductEntityMapper,
) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> =
        productDao
            .getProducts()
            .map { entities ->
                entities.map { productEntityMapper.toDomain(it) }
            }.onStart {
                try {
                    if (productDao.getProducts().firstOrNull()?.isEmpty() == true) {
                        val apiProducts = productApiService.getProducts()

                        val productEntities =
                            apiProducts
                                .map {
                                    productApiMapper.toDomain(it)
                                }.map {
                                    productEntityMapper.toEntity(it)
                                }
                        productDao.insertProducts(productEntities)
                    }
                } catch (e: Exception) {
                    throw e
                }
            }

    override fun searchProductsWithFilters(
        query: String,
        minPrice: Double?,
        maxPrice: Double?,
        category: String?,
        minCount: Int?,
        minRating: Double?,
    ): Flow<List<Product>> =
        productDao
            .searchProductsWithFilters(query = query, minPrice = minPrice, maxPrice = maxPrice, category = category, minCount = minCount, minRate = minRating)
            .map { entities ->
                entities.map { productEntityMapper.toDomain(it) }
            }

    override fun getProductById(id: Int): Product = productDao.getProductById(id).toDomainModel()
}