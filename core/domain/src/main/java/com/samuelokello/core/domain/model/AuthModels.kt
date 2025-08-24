package com.samuelokello.core.domain.model

data class UserCredentials(
    val username: String,
    val password: String,
    val rememberMe: Boolean = false,
)

data class SessionTokens(
    val accessToken: String,
    val refreshToken: String?,
)

data class SessionTokensRequest(
    val refreshToken: String?,
)

data class AuthenticatedUser(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val profileImageUrl: String,
)

data class LoginResponse(
    val authenticatedUser: AuthenticatedUser,
    val sessionTokens: SessionTokens,
)