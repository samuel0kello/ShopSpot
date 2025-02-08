package com.samuelokello.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val profilePic: String?
)
