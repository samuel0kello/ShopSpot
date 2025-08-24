package com.samuelokello.datasource.local.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val sku: String,
    val weight: Double,
    val warrantyInformation: String?,
    val shippingInformation: String?,
    val availabilityStatus: String?,
    val returnPolicy: String?,
    val minimumOrderQuantity: Int,
    val thumbnail: String?,
)