package com.samuelokello.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshSessionRequestDto(
    val refreshToken: String,
)