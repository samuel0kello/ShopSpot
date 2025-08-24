package com.samuelokello.remote.sources.auth

import com.samuelokello.core.domain.util.DataError
import com.samuelokello.core.domain.util.Result
import com.samuelokello.remote.models.CurrentUserResponseDto
import com.samuelokello.remote.models.LoginRequestDto
import com.samuelokello.remote.models.LoginResponseDto
import com.samuelokello.remote.models.RefreshSessionRequestDto
import com.samuelokello.remote.models.RefreshSessionResponseDto
import com.samuelokello.remote.util.get
import com.samuelokello.remote.util.post
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