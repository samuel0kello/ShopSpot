package com.samuelokello.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dimensions")
data class DimensionsEntity(
    @PrimaryKey val productId: Int,
    val width: Double,
    val height: Double,
    val depth: Double,
)