package com.samuelokello.core.domain.repository

import com.samuelokello.core.domain.model.UserCart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getUserCarts(userId: Int): Flow<Result<List<UserCart>>>

    suspend fun addItemToCart(
        userId: Int,
        productId: Int,
        quantity: Int,
    ): Flow<Result<UserCart>>

    suspend fun removeItemFromCart(
        userId: Int,
        productId: Int,
    ): Flow<Result<UserCart>>

    suspend fun updateItemQuantity(
        userId: Int,
        productId: Int,
        quantity: Int,
    ): Flow<Result<UserCart>>

    suspend fun clearCart(userId: Int): Flow<Result<Unit>>

    suspend fun refreshCarts(userId: Int): Flow<Result<List<UserCart>>>
}
