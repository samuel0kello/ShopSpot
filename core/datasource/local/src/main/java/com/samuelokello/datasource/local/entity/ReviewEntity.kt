package com.samuelokello.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val reviewId: Int = 0,
    val productId: Int,
    val rating: Int,
    val comment: String,
    val date: String, // Store as ISO string or use Date type with converters
    val reviewerName: String,
    val reviewerEmail: String,
)