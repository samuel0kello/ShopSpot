package com.samuelokello.datasource.local.source.product

import com.samuelokello.core.domain.model.Product
import com.samuelokello.datasource.local.db.product.ProductDao
import kotlinx.coroutines.flow.Flow

interface ProductLocalSource {
    suspend fun getProducts(): Flow<List<Product>>

    fun searchProductsWithFilters(
        query: String,
        minPrice: Double?,
        maxPrice: Double?,
        category: String?,
        minCount: Int?,
        minRating: Double?,
    ): Flow<List<Product>>

    suspend fun getProductById(id: Int): Product
}

class ProductLocalSourceImpl(
    private val dao: ProductDao,
) : ProductLocalSource {
    override suspend fun getProducts(): Flow<List<Product>> {
        TODO("Not yet implemented")
//         dao
//            .getAllProducts()
//            .map { entities ->
//                entities.map { }
//            }
//            .onStart {
//                try {
//                    when (dao.getAllProducts().firstOrNull()?.isEmpty() == true) {
//                        true -> {
//                        }
//
//                        false -> TODO()
//                    }
//                } catch (e: Exception) {
//
//                }
// //                catch (e:DataError.Local) {
// //                    when(e) {
// //                        DataError.Local.DISK_FULL -> TODO()
// //                        DataError.Local.CACHE_CORRUPTION -> TODO()
// //                        DataError.Local.INVALID_DATA_FORMAT -> TODO()
// //                        DataError.Local.PERMISSION_DENIED -> TODO()
// //                    }
// //                }
//            }
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

    override suspend fun getProductById(id: Int): Product {
        TODO("Not yet implemented")
//        return dao.getProductById(id)
    }
}
