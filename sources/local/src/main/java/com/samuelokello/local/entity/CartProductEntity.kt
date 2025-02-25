package com.samuelokello.local.entity

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity(tableName = "cart_products")
@Serializable
data class CartProductEntity(
    val productId: Int,
    val quantity: Int,
)
