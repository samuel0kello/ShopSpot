// package com.samuelokello.shopspot.ui.home
//
// import com.samuelokello.core.data.repository.ProductRepository
// import com.samuelokello.core.fake.FakeProductRepository
// import com.samuelokello.core.model.Product
// import com.samuelokello.core.rule.TestDispatcherRule
// import com.samuelokello.shopspot.fake.FakeDataSource
// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.flow.Flow
// import kotlinx.coroutines.flow.first
// import kotlinx.coroutines.flow.flow
// import kotlinx.coroutines.test.advanceUntilIdle
// import kotlinx.coroutines.test.runTest
// import org.junit.Assert.*
// import org.junit.Rule
// import org.junit.Test
//
// @OptIn(ExperimentalCoroutinesApi::class)
// class ProductViewModelTest {
//    @get:Rule
//    val testDispatcher =
//        com.samuelokello.core.rule
//            .TestDispatcherRule()
//
//    @Test
//    fun homeViewModel_loadProducts_verifyHomeUiStateSuccess() =
//        runTest {
//            val homeViewModel =
//                com.samuelokello.home.HomeViewModel(
//                    repository =
//                        com.samuelokello.core.fake
//                            .FakeProductRepository(),
//                )
//
//            homeViewModel.loadProducts()
//
//            advanceUntilIdle()
//
//            val expectedProducts = FakeDataSource.fakeProductsList.first()
//            val expectedState = homeViewModel.homeUiState.value
//
//            assertEquals(
//                com.samuelokello.home.HomeUiState
//                    .Success(expectedProducts),
//                expectedState,
//            )
//        }
//
//    @Test
//    fun homeViewModel_loadProducts_verifyErrorState() =
//        runTest {
//            val errorRepository =
//                object : com.samuelokello.core.data.repository.ProductRepository {
//                    override fun getProducts(): Flow<List<com.samuelokello.core.model.Product>> =
//                        flow {
//                            throw Exception("Network Error")
//                        }
//
//                    override fun searchProductsWithFilters(
//                        query: String,
//                        minPrice: Double?,
//                        maxPrice: Double?,
//                        category: String?,
//                        minCount: Int?,
//                        minRating: Double?,
//                    ): Flow<List<com.samuelokello.core.model.Product>> {
//                        TODO("Not yet implemented")
//                    }
//
//                    override fun getProductById(id: Int): com.samuelokello.core.model.Product {
//                        TODO("Not yet implemented")
//                    }
//                }
//            val homeViewModel = com.samuelokello.home.HomeViewModel(repository = errorRepository)
//
//            homeViewModel.loadProducts()
//
//            advanceUntilIdle()
//
//            val expectedState = homeViewModel.homeUiState.value
//            assertEquals(com.samuelokello.home.HomeUiState.Error, expectedState)
//        }
// }
