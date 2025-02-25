package com.samuelokello.cart

import com.samuelokello.core.model.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1,
)
