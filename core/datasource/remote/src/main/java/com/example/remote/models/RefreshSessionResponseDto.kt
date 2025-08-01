package com.example.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshSessionResponseDto(
    val accessToken: String,
    val refreshToken: String,
)
