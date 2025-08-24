package com.samuelokello.datasource.local.source.auth

import com.samuelokello.datasource.local.source.preference.PreferenceHelper
import com.samuelokello.datasource.local.source.preference.PreferenceKeys
import kotlinx.coroutines.flow.Flow

interface AuthenticationLocalSource {
    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    fun getAccessToken(): Flow<String?>

    fun getRefreshToken(): Flow<String?>

    suspend fun clearTokens()
}

class AuthenticationLocalSourceImpl(
    private val remotePreferencesHelper: PreferenceHelper,
) : AuthenticationLocalSource {
    override suspend fun saveAccessToken(token: String) {
        remotePreferencesHelper.save(PreferenceKeys.ACCESS_TOKEN, token)
    }

    override suspend fun saveRefreshToken(token: String) {
        remotePreferencesHelper.save(PreferenceKeys.REFRESH_TOKEN, token)
    }

    override fun getAccessToken(): Flow<String?> = remotePreferencesHelper.get(PreferenceKeys.ACCESS_TOKEN)

    override fun getRefreshToken(): Flow<String?> = remotePreferencesHelper.get(PreferenceKeys.REFRESH_TOKEN)

    override suspend fun clearTokens() {
        remotePreferencesHelper.delete(PreferenceKeys.ACCESS_TOKEN)
        remotePreferencesHelper.delete(PreferenceKeys.REFRESH_TOKEN)
    }
}