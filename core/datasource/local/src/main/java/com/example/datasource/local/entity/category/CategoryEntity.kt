package com.example.datasource.local.entity.category

import androidx.room.Entity

@Entity(tableName = "categories")
data class CategoryEntity(
    val slug: String,
    val name: String,
    val url: String,
)