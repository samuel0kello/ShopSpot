package com.samuelokello.core.model

data class UserCart(
    val id: Int,
    val date: String,
    val products: List<CartProduct>,
    val userId: Int,
    val v: Int? = null,
)
