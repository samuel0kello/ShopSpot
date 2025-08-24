package com.samuelokello.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: Int,
    val userName: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
)