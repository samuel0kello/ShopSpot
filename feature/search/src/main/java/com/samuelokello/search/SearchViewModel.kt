package com.samuelokello.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelokello.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val repository: ProductRepository,
    ) : ViewModel() {
        private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
        val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

        private val recentSearches = MutableStateFlow<List<String>>(emptyList())

        private val allProductNames =
            listOf(
                "Laptop",
                "Phone",
                "Headphones",
                "Keyboard",
                "Monitor",
                "Chair",
                "Table",
                "Mouse",
                "Tablet",
                "Printer",
            )

        fun getSuggestions(query: String): List<String> =
            if (query.isNotBlank()) {
                allProductNames
                    .filter {
                        it.contains(query, ignoreCase = true)
                    }.take(5)
            } else {
                emptyList()
            }

        fun addToHistory(query: String) {
            recentSearches.value = (listOf(query) + recentSearches.value).distinct().take(10)
        }

        fun removeFromHistory(query: String) {
            recentSearches.value = recentSearches.value.filter { it != query }
        }

        fun getRecentSearches(): StateFlow<List<String>> = recentSearches

        fun search(
            query: String = "",
            minPrice: Double? = null,
            maxPrice: Double? = null,
            category: String? = null,
            minCount: Int? = null,
            minRating: Double? = null,
        ) {
            if (query.isBlank()) {
                _searchUiState.value = SearchUiState.Success(emptyList())
                return
            }

            viewModelScope.launch {
                _searchUiState.value = SearchUiState.Loading
                try {
                    repository
                        .searchProductsWithFilters(
                            query = query,
                            minPrice = minPrice,
                            maxPrice = maxPrice,
                            category = category,
                            minCount = minCount,
                            minRating = minRating,
                        ).collect { products ->
                            _searchUiState.value = SearchUiState.Success(products)
                        }
                } catch (e: Exception) {
                    _searchUiState.value = SearchUiState.Error("Error occurred")
                }
            }
        }
    }

sealed interface SearchUiState {
    data object Loading : SearchUiState

    data class Error(
        val message: String,
    ) : SearchUiState

    data class Success(
        val products: List<com.samuelokello.core.model.Product>,
    ) : SearchUiState
}
