package com.samuelokello.datasource.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String?,
)
