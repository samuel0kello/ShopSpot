package com.samuelokello.repository

import com.samuelokello.core.model.Product
import com.samuelokello.domain.repository.ProductRepository
import com.samuelokello.local.dao.ProductDao
import com.samuelokello.network.product.ProductApiService
import com.samuelokello.repository.mapper.ProductApiMapper
import com.samuelokello.repository.mapper.ProductEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl
    @Inject
    constructor(
        private val productApiService: ProductApiService,
        private val productDao: ProductDao,
        private val productApiMapper: ProductApiMapper,
        private val productEntityMapper: ProductEntityMapper,
    ) : ProductRepository {
        override fun getProducts(): Flow<List<Product>> {
            TODO("Not yet implemented")
        }
//        override fun getProducts(): Flow<List<Product>> =
//            productDao
//                .getProducts()
//                .map { entries ->
//                    entries.map { productEntityMapper.toDomain(it) }
//                }.onStart {
//                    try {
//                        if (productDao.getProducts().firstOrNull()?.isEmpty() == true) {
//                            val remoteProducts = productApiService.getProducts()
//
//                            val productEntities =
//                                remoteProducts
//                                    .map {
//                                        productApiMapper.toDomain(it)
//                                    }.map {
//                                        productEntityMapper.toEntity(it)
//                                    }
//
//                            productDao.insertProducts(productEntities)
//                        }
//                    } catch (e: Exception) {
//                        throw e
//                    }
//                }

        override fun searchProductsWithFilters(
            query: String,
            minPrice: Double?,
            maxPrice: Double?,
            category: String?,
            minCount: Int?,
            minRating: Double?,
        ): Flow<List<Product>> =
            productDao
                .searchProductsWithFilters(
                    query,
                    minPrice,
                    maxPrice,
                    category,
                    minCount,
                    minRating,
                ).map { entities ->
                    entities.map { productEntityMapper.toDomain(it) }
                }

        override fun getProductById(id: Int): Product {
            TODO("Not yet implemented")
        }

        // override fun getProductById(id: Int): Product = productDao.getProductById(id)
    }
