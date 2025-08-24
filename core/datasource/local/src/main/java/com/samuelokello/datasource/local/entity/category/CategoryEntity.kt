package com.samuelokello.datasource.local.entity.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val slug: String,
    val name: String,
    val url: String,
)