package com.example.data.fake

import com.samuelokello.core.domain.util.DataError
import com.samuelokello.core.domain.util.Result
import com.samuelokello.remote.models.CurrentUserResponseDto
import com.samuelokello.remote.models.LoginRequestDto
import com.samuelokello.remote.models.LoginResponseDto
import com.samuelokello.remote.models.RefreshSessionRequestDto
import com.samuelokello.remote.models.RefreshSessionResponseDto
import com.samuelokello.remote.sources.auth.AuthenticationRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeAuthenticationRemoteSource : AuthenticationRemoteSource {
    private val loginResponse = MutableSharedFlow<Result<LoginResponseDto, DataError.Network>>(replay = 1)
    var lastLoginRequest: LoginRequestDto? = null

    suspend fun emitLoginResponse(response: Result<LoginResponseDto, DataError.Network>) {
        loginResponse.emit(response)
    }

    override suspend fun login(loginRequest: LoginRequestDto): Flow<Result<LoginResponseDto, DataError.Network>> {
        lastLoginRequest = loginRequest
        return loginResponse
    }

    private val currentUserResponse = MutableSharedFlow<Result<CurrentUserResponseDto, DataError.Network>>(replay = 1)
    var lastToken: String? = null
        private set

    suspend fun emitCurrentUserResponse(response: Result<CurrentUserResponseDto, DataError.Network>) {
        currentUserResponse.emit(response)
    }

    override suspend fun getCurrentAuthenticatedUser(token: String): Flow<Result<CurrentUserResponseDto, DataError.Network>> {
        lastToken = token
        return currentUserResponse
    }

    private var refreshTokensBehavior: ((RefreshSessionRequestDto) -> Result<RefreshSessionResponseDto, DataError.Network>)? = null
    var lastRefreshTokenRequest: RefreshSessionRequestDto? = null
        private set

    fun setRefreshTokenBehavior(behavior: (RefreshSessionRequestDto) -> Result<RefreshSessionResponseDto, DataError.Network>) {
        this.refreshTokensBehavior = behavior
    }

    override suspend fun refreshTokens(refreshToken: RefreshSessionRequestDto): Result<RefreshSessionResponseDto, DataError.Network> {
        lastRefreshTokenRequest = refreshToken
        return refreshTokensBehavior?.invoke(refreshToken)
            ?: throw IllegalStateException("Refresh token not set in FakeAuthenticationRemoteSource")
    }

    fun reset() {
        lastLoginRequest = null
        lastToken = null
        lastRefreshTokenRequest = null
        refreshTokensBehavior = null
    }
}