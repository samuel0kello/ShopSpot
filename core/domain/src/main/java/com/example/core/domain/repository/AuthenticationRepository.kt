package com.example.core.domain.repository

import com.example.core.domain.model.AuthenticatedUser
import com.example.core.domain.model.LoginResponse
import com.example.core.domain.model.SessionTokens
import com.example.core.domain.model.SessionTokensRequest
import com.example.core.domain.model.UserCredentials
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun getAccessToken(): Flow<String?>

    fun getRefreshToken(): Flow<String?>

    suspend fun saveTokens(
        accessToken: String,
        refreshToken: String,
    )

    suspend fun clearTokens()

    suspend fun performTokenRefresh(): Result<SessionTokens, DataError.Network>

    suspend fun login(loginRequest: UserCredentials): Flow<Result<LoginResponse, DataError.Network>>

    suspend fun getCurrentAuthenticatedUser(token: String): Flow<Result<AuthenticatedUser, DataError.Network>>

    suspend fun logout()

    fun isUserLoggedIn(): Flow<Boolean>

    suspend fun refreshAccessToken(refreshToken: SessionTokensRequest): Result<SessionTokens, DataError.Network>
}
