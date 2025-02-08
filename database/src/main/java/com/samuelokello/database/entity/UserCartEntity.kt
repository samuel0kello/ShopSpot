package com.samuelokello.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.samuelokello.database.converters.CartProductConverter

@Entity(tableName = "user_carts")
data class UserCartEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    @TypeConverters(CartProductConverter::class)
    val products: List<CartProductEntity>,
    val userId: Int,
    val v: Int? = null
)
