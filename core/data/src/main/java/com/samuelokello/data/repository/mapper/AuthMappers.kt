package com.samuelokello.data.repository.mapper

import com.samuelokello.core.domain.model.AuthenticatedUser
import com.samuelokello.core.domain.model.LoginResponse
import com.samuelokello.core.domain.model.SessionTokens
import com.samuelokello.core.domain.model.SessionTokensRequest
import com.samuelokello.core.domain.model.UserCredentials
import com.samuelokello.remote.models.CurrentUserResponseDto
import com.samuelokello.remote.models.LoginRequestDto
import com.samuelokello.remote.models.LoginResponseDto
import com.samuelokello.remote.models.RefreshSessionRequestDto

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