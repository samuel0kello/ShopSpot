package com.example.data.repository

import com.example.core.domain.model.AuthenticatedUser
import com.example.core.domain.model.LoginResponse
import com.example.core.domain.model.SessionTokens
import com.example.core.domain.model.SessionTokensRequest
import com.example.core.domain.model.UserCredentials
import com.example.core.domain.repository.AuthenticationRepository
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.domain.util.map
import com.example.data.mapper.toDomainAuthenticatedUser
import com.example.data.mapper.toDomainLoginResponse
import com.example.data.mapper.toLoginRequestDto
import com.example.data.mapper.toRefreshSessionRequestDto
import com.example.datasource.local.source.auth.AuthenticationLocalSource
import com.example.remote.models.RefreshSessionRequestDto
import com.example.remote.sources.auth.AuthenticationRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map as flowMap

class AuthenticationRepositoryImpl(
    private val localSource: AuthenticationLocalSource,
    private val remoteSource: AuthenticationRemoteSource,
) : AuthenticationRepository {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getAccessToken(): Flow<String?> = localSource.getAccessToken()

    override fun getRefreshToken(): Flow<String?> = localSource.getRefreshToken()

    override suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    ) {
        localSource.saveAccessToken(accessToken)
        localSource.saveRefreshToken(refreshToken)
    }

    override suspend fun clearTokens() {
        localSource.clearTokens()
    }

    override suspend fun performTokenRefresh(): Result<SessionTokens, DataError.Network> {
        val currentRefreshToken = localSource.getRefreshToken().firstOrNull()

        if (currentRefreshToken.isNullOrBlank()) {
            return Result.Error(DataError.Network.NO_INTERNET)
        }
        val requestDto = RefreshSessionRequestDto(currentRefreshToken)
        val refreshTokenDto = remoteSource.refreshTokens(requestDto)

        return refreshTokenDto
            .map { refreshSessionResponseDto ->
                val sessionTokens =
                    SessionTokens(
                        accessToken = refreshSessionResponseDto.accessToken,
                        refreshToken = refreshSessionResponseDto.refreshToken,
                    )
                scope.launch {
                    saveTokens(sessionTokens.accessToken, sessionTokens.refreshToken ?: "")
                }
                sessionTokens
            }.also {
                if (refreshTokenDto is Result.Error) {
                    clearTokens()
                }
            }
    }

    override suspend fun login(loginRequest: UserCredentials): Flow<Result<LoginResponse, DataError.Network>> {
        val loginRequestDto = loginRequest.toLoginRequestDto()
        val remoteFlow = remoteSource.login(loginRequestDto)

        return remoteFlow.flowMap { resultDto ->
            resultDto.map { loginResponseDto ->
                val loginResponse = loginResponseDto.toDomainLoginResponse()

                scope.launch {
                    saveTokens(loginResponse.sessionTokens.accessToken, loginResponse.sessionTokens.refreshToken ?: "")
                }
                loginResponse
            }
        }
    }

    override suspend fun getCurrentAuthenticatedUser(token: String): Flow<Result<AuthenticatedUser, DataError.Network>> =
        remoteSource.getCurrentAuthenticatedUser(token).map { resultDto ->
            resultDto.map { currentUserResponseDto ->
                currentUserResponseDto.toDomainAuthenticatedUser()
            }
        }

    override suspend fun refreshAccessToken(refreshToken: SessionTokensRequest): Result<SessionTokens, DataError.Network> {
        val refreshTokenDto = refreshToken.toRefreshSessionRequestDto()
        return remoteSource.refreshTokens(refreshTokenDto).map { refreshSessionResponseDto ->
            val sessionTokens =
                SessionTokens(
                    accessToken = refreshSessionResponseDto.accessToken,
                    refreshToken = refreshSessionResponseDto.refreshToken,
                )

            scope.launch {
                saveTokens(sessionTokens.accessToken, sessionTokens.refreshToken ?: "")
            }
            sessionTokens
        }
    }

    override suspend fun logout() {
        clearTokens()
    }

    override fun isUserLoggedIn(): Flow<Boolean> =
        getAccessToken().flowMap { accessToken ->
            accessToken != null
        }
}
