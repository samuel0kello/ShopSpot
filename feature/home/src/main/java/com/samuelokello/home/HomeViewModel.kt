package com.samuelokello.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelokello.core.model.Product
import com.samuelokello.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel
 */
@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val getProductsUseCase: GetProductsUseCase,
    ) : ViewModel() {
        private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
        var homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

        fun loadProducts() {
            viewModelScope.launch {
                getProductsUseCase()
                    .onStart {
                        _homeUiState.value = HomeUiState.Loading
                    }.catch {
                        _homeUiState.value = HomeUiState.Error(message = it.message ?: " ")
                    }.collect { product ->
                        _homeUiState.value = HomeUiState.Success(product)
                    }
            }
        }

        init {
            loadProducts()
        }
    }

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Error(
        val message: String,
    ) : HomeUiState

    data class Success(
        val products: List<Product>,
    ) : HomeUiState
}
