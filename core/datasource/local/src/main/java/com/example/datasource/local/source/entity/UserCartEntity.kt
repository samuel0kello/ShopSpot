package com.example.datasource.local.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_carts")
data class UserCartEntity(
    @PrimaryKey val userCartId: Int,

)
