package com.example.data.mapper

import com.example.core.domain.model.AuthenticatedUser
import com.example.core.domain.model.LoginResponse
import com.example.core.domain.model.SessionTokens
import com.example.core.domain.model.SessionTokensRequest
import com.example.core.domain.model.UserCredentials
import com.example.remote.models.CurrentUserResponseDto
import com.example.remote.models.LoginRequestDto
import com.example.remote.models.LoginResponseDto
import com.example.remote.models.RefreshSessionRequestDto

fun LoginResponseDto.toDomainSessionTokens(): SessionTokens =
    SessionTokens(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )

fun SessionTokensRequest.toRefreshSessionRequestDto(): RefreshSessionRequestDto =
    RefreshSessionRequestDto(
        refreshToken = this.refreshToken ?: "",
    )

fun LoginResponseDto.toDomainLoginResponse(): LoginResponse {
    val user =
        AuthenticatedUser(
            id = this.id,
            username = this.userName,
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            gender = this.gender,
            profileImageUrl = this.image,
        )

    return LoginResponse(
        authenticatedUser = user,
        sessionTokens = this.toDomainSessionTokens(),
    )
}

fun CurrentUserResponseDto.toDomainAuthenticatedUser(): AuthenticatedUser =
    AuthenticatedUser(
        id = this.id,
        username = this.userName,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        profileImageUrl = this.image,
    )

fun UserCredentials.toLoginRequestDto(): LoginRequestDto =
    LoginRequestDto(
        username = this.username,
        password = this.password,
    )