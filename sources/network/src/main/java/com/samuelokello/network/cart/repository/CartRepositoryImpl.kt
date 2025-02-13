package com.samuelokello.network.cart.repository

import com.samuelokello.core.model.UserCart
import com.samuelokello.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl : CartRepository {
    override suspend fun getUserCarts(userId: Int): Flow<Result<List<UserCart>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addItemToCart(
        userId: Int,
        productId: Int,
        quantity: Int,
    ): Flow<Result<UserCart>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemFromCart(
        userId: Int,
        productId: Int,
    ): Flow<Result<UserCart>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemQuantity(
        userId: Int,
        productId: Int,
        quantity: Int,
    ): Flow<Result<UserCart>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearCart(userId: Int): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshCarts(userId: Int): Flow<Result<List<UserCart>>> {
        TODO("Not yet implemented")
    }
}
