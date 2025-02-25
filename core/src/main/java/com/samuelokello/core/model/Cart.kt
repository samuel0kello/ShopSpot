package com.samuelokello.core.model

data class Cart(
    val date: String,
    val id: Int,
    val cartProduct: List<CartProduct>,
    val userId: Int,
    val v: Int,
)
