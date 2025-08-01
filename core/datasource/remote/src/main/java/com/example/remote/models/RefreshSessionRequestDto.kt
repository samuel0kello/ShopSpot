package com.example.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshSessionRequestDto(
    val refreshToken: String,
)