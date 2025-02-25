// package com.samuelokello.shopspot.ui.search
//
// import com.samuelokello.core.fake.FakeProductRepository
// import com.samuelokello.core.model.Product
// import com.samuelokello.core.rule.TestDispatcherRule
// import com.samuelokello.shopspot.fake.FakeDataSource
// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.flow.first
// import kotlinx.coroutines.launch
// import kotlinx.coroutines.test.UnconfinedTestDispatcher
// import kotlinx.coroutines.test.advanceUntilIdle
// import kotlinx.coroutines.test.runTest
// import org.junit.Assert.*
// import org.junit.Before
// import org.junit.Rule
// import org.junit.Test
//
// @OptIn(ExperimentalCoroutinesApi::class)
// class SearchViewModelTest {
//    @get:Rule
//    val testDispatcher =
//        com.samuelokello.core.rule
//            .TestDispatcherRule()
//
//    private lateinit var repository: com.samuelokello.core.fake.FakeProductRepository
//    private lateinit var viewModel: com.samuelokello.search.SearchViewModel
//
//    @Before
//    fun setup() {
//        repository =
//            com.samuelokello.core.fake
//                .FakeProductRepository()
//        viewModel = com.samuelokello.search.SearchViewModel(repository)
//    }
//
//    @Test
//    fun searchProducts_whenSuccessful_updatesStateToSuccess() =
//        runTest {
//            // Keep track of emitted states
//            val states = mutableListOf<com.samuelokello.search.SearchUiState>()
//
//            // Start collecting states
//            val job =
//                launch(UnconfinedTestDispatcher(testScheduler)) {
//                    viewModel.searchUiState.collect { state ->
//                        states.add(state)
//                    }
//                }
//
//            // Initial state should be Loading
//            assertEquals(com.samuelokello.search.SearchUiState.Loading, states.last())
//
//            // Perform search
//            viewModel.search("s")
//
//            // Advance time to allow Flow to complete
//            advanceUntilIdle()
//
//            // Verify final state
//            assertTrue(
//                "Expected Success state but was ${states.last()}. All states: $states",
//                states.last() is com.samuelokello.search.SearchUiState.Success,
//            )
//
//            // Optional: Verify the content of Success state
//            val finalState = states.last() as com.samuelokello.search.SearchUiState.Success
//            assertEquals(FakeDataSource.fakeProductsList.first(), finalState.products)
//
//            // Cleanup
//            job.cancel()
//        }
//
//    @Test
//    fun searchProducts_withEmptyQuery_returnsEmptyList() =
//        runTest {
//            viewModel.search("")
//            advanceUntilIdle()
//
//            assertTrue(viewModel.searchUiState.value is com.samuelokello.search.SearchUiState.Success)
//            assertEquals(
//                emptyList<com.samuelokello.core.model.Product>(),
//                (viewModel.searchUiState.value as com.samuelokello.search.SearchUiState.Success).products,
//            )
//        }
// }
