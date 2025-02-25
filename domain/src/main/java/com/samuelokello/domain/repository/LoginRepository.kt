package com.samuelokello.domain.repository

interface LoginRepository {
    suspend fun login(
        username: String,
        password: String,
        rememberMe: Boolean,
    )

    suspend fun autoLogin()

    suspend fun logout()
}
