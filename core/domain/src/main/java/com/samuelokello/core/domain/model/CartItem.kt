package com.samuelokello.core.domain.model

data class CartItem(
    val product: Product,
    var quantity: Int = 1,
)