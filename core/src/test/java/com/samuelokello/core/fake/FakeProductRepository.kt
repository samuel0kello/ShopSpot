// package com.samuelokello.core.fake
//
// import com.samuelokello.core.data.repository.ProductRepository
// import com.samuelokello.core.model.Product
// import kotlinx.coroutines.flow.Flow
//
// class FakeProductRepository: com.samuelokello.core.data.repository.ProductRepository {
//    override fun getProducts(): Flow<List<com.samuelokello.core.model.Product>> = FakeDataSource.fakeProductsList
//
//
//    override fun searchProductsWithFilters(
//        query: String,
//        minPrice: Double?,
//        maxPrice: Double?,
//        category: String?,
//        minCount: Int?,
//        minRating: Double?
//    ): Flow<List<com.samuelokello.core.model.Product>> = FakeDataSource.fakeProductsList
//
//    override fun getProductById(id: Int): com.samuelokello.core.model.Product {
//        TODO("Not yet implemented")
//    }
//
// }
