// package com.samuelokello.core.model.mapper
//
// import com.samuelokello.core.model.UserCart
// import com.samuelokello.local.entity.UserCartEntity
// import com.samuelokello.shopspot.data.network.cart.dto.CartProductDto
// import com.samuelokello.shopspot.data.network.cart.dto.UserCartResponseDto
//
// // DTO to Domain
// fun UserCartResponseDto.toDomain() =
//    UserCart(
//        id = id,
//        date = date,
//        products = cartProductDto.map { it.toDomain() },
//        userId = userId,
//        v = v,
//    )
//
// fun CartProductDto.toDomain() =
//    com.samuelokello.core.model.CartProduct(
//        productId = productId,
//        quantity = quantity,
//    )
//
// // Domain to Entity
// fun com.samuelokello.core.model.UserCart.toEntity() =
//    UserCartEntity(
//        id = id,
//        date = date,
//        products = products.map { it.toEntity() },
//        userId = userId,
//        v = v,
//    )
//
// fun com.samuelokello.core.model.CartProduct.toEntity() =
//    CartProductEntity(
//        productId = productId,
//        quantity = quantity,
//    )
//
// // Entity to Domain
// fun com.samuelokello.core.database.entity.UserCartEntity.toDomain() =
//    com.samuelokello.core.model.UserCart(
//        id = id,
//        date = date,
//        products = products.map { it.toDomain() },
//        userId = userId,
//        v = v,
//    )
//
// fun com.samuelokello.core.database.entity.CartProductEntity.toDomain() =
//    com.samuelokello.core.model.CartProduct(
//        productId = productId,
//        quantity = quantity,
//    )
//
// // fun UserCart.toDto() = UserCartRequestDto(
// //    userId = userId,
// //    products = products.map { it.toDto() }
// // )
// //
// // fun CartProduct.toDto() = CartProductRequestDto(
// //    productId = productId,
// //    quantity = quantity
// // )
