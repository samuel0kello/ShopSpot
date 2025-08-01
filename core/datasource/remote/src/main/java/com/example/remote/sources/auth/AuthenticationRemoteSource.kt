package com.example.remote.sources.auth

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.remote.models.CurrentUserResponseDto
import com.example.remote.models.LoginRequestDto
import com.example.remote.models.LoginResponseDto
import com.example.remote.models.RefreshSessionRequestDto
import com.example.remote.models.RefreshSessionResponseDto
import com.example.remote.util.get
import com.example.remote.util.post
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface AuthenticationRemoteSource {
    suspend fun login(loginRequest: LoginRequestDto): Flow<Result<LoginResponseDto, DataError.Network>>

    suspend fun getCurrentAuthenticatedUser(token: String): Flow<Result<CurrentUserResponseDto, DataError.Network>>

    suspend fun refreshTokens(refreshToken: RefreshSessionRequestDto): Result<RefreshSessionResponseDto, DataError.Network>
}

class AuthenticationRemoteSourceImpl(
    private val httpClient: HttpClient,
) : AuthenticationRemoteSource {
    private object ApiRoutes {
        const val LOGIN = "auth/login"
        const val CURRENT_USER = "auth/me"
        const val REFRESH_TOKEN = "auth/refresh"
    }

    override suspend fun login(loginRequest: LoginRequestDto): Flow<Result<LoginResponseDto, DataError.Network>> {
        val request =
            httpClient.post<LoginRequestDto, LoginResponseDto>(
                path = ApiRoutes.LOGIN,
                body = loginRequest,
            )

        return when (request) {
            is Result.Success<LoginResponseDto> -> {
                flowOf(Result.Success(request.data))
            }

            is Result.Error<DataError.Network> -> {
                flowOf(Result.Error(request.error))
            }
        }
    }

    override suspend fun getCurrentAuthenticatedUser(token: String): Flow<Result<CurrentUserResponseDto, DataError.Network>> {
        val request =
            httpClient.get<CurrentUserResponseDto>(
                path = ApiRoutes.CURRENT_USER,
            )

        return when (request) {
            is Result.Success<CurrentUserResponseDto> -> {
                flowOf(Result.Success(request.data))
            }
            is Result.Error<DataError.Network> -> {
                flowOf(Result.Error(request.error))
            }
        }
    }

    override suspend fun refreshTokens(refreshToken: RefreshSessionRequestDto): Result<RefreshSessionResponseDto, DataError.Network> {
        val request =
            httpClient.post<RefreshSessionRequestDto, RefreshSessionResponseDto>(
                path = ApiRoutes.REFRESH_TOKEN,
                body = refreshToken,
            )

        return when (request) {
            is Result.Success<RefreshSessionResponseDto> -> {
                Result.Success(request.data)
            }
            is Result.Error<DataError.Network> -> {
                Result.Error(request.error)
            }
        }
    }
}