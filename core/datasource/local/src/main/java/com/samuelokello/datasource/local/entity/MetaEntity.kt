package com.samuelokello.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meta")
data class MetaEntity(
    @PrimaryKey val productId: Int,
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String?,
)