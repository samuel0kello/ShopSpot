package com.samuelokello.repository.mapper

import com.samuelokello.core.model.CartProduct
import com.samuelokello.core.model.UserCart
import com.samuelokello.local.entity.CartProductEntity
import com.samuelokello.local.entity.UserCartEntity
import com.samuelokello.shopspot.data.network.cart.dto.CartProductDto
import com.samuelokello.shopspot.data.network.cart.dto.UserCartResponseDto

// DTO to Domain
fun UserCartResponseDto.toDomain() =
    UserCart(
        id = id,
        date = date,
        products = cartProductDto.map { it.toDomain() },
        userId = userId,
        v = v,
    )

fun CartProductDto.toDomain() =
    CartProduct(
        productId = productId,
        quantity = quantity,
    )

// Domain to Entity
fun UserCart.toEntity() =
    UserCartEntity(
        id = id,
        date = date,
        products = products.map { it.toEntity() },
        userId = userId,
        v = v,
    )

fun CartProduct.toEntity() =
    CartProductEntity(
        productId = productId,
        quantity = quantity,
    )

// Entity to Domain
fun UserCartEntity.toDomain() =
    UserCart(
        id = id,
        date = date,
        products = products.map { it.toDomain() },
        userId = userId,
        v = v,
    )

fun CartProductEntity.toDomain() =
    CartProduct(
        productId = productId,
        quantity = quantity,
    )
