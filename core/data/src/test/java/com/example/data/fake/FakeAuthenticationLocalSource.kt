package com.example.data.fake

import com.samuelokello.datasource.local.source.auth.AuthenticationLocalSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeAuthenticationLocalSource : AuthenticationLocalSource {
    private val _accessTokenFlow = MutableStateFlow<String?>(null)
    private val _refreshTokenFlow = MutableStateFlow<String?>(null)

    var saveAccessTokenCalledWith: String? = null
        private set
    var saveRefreshTokenCalledWith: String? = null
        private set
    var clearTokensCalled = false
        private set

    override suspend fun saveAccessToken(token: String) {
        saveAccessTokenCalledWith = token
        _accessTokenFlow.update { token }
    }

    override suspend fun saveRefreshToken(token: String) {
        saveRefreshTokenCalledWith = token
        _refreshTokenFlow.update { token }
    }

    override fun getAccessToken(): Flow<String?> = _accessTokenFlow.asStateFlow()

    override fun getRefreshToken(): Flow<String?> = _refreshTokenFlow.asStateFlow()

    override suspend fun clearTokens() {
        clearTokensCalled = true
        _accessTokenFlow.update { null }
        _refreshTokenFlow.update { null }
    }

    fun setInitialTokens(
        accessToken: String?,
        refreshToken: String?,
    ) {
        _accessTokenFlow.value = accessToken
        _refreshTokenFlow.value = refreshToken
    }

    fun resetState() {
        _accessTokenFlow.value = null
        _refreshTokenFlow.value = null
        saveAccessTokenCalledWith = null
        saveRefreshTokenCalledWith = null
        clearTokensCalled = false
    }
}