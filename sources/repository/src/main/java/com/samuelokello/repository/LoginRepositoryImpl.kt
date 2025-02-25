package com.samuelokello.repository

import com.samuelokello.core.model.UserData
import com.samuelokello.core.preference.AuthPreferences
import com.samuelokello.domain.repository.LoginRepository
import com.samuelokello.network.auth.AuthApiService
import com.samuelokello.network.auth.dto.UserResponseDto
import com.samuelokello.shopspot.data.network.auth.request.LoginRequest
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl
    @Inject
    constructor(
        private val authApiService: AuthApiService,
        private val authPreferences: AuthPreferences,
    ) : LoginRepository {
        override suspend fun login(
            username: String,
            password: String,
            rememberMe: Boolean,
        ) {
            try {
                val loginRequest = LoginRequest(username, password)
                val response = authApiService.loginUser(loginRequest)

                // Save user data if available
                getAllUsers(loginRequest.username)?.let { data ->
                    val userData =
                        UserData(
                            data.id.toString(),
                            data.email,
                            data.name.toString(),
                        )
                    authPreferences.saveUserData(userData)
                }

                if (rememberMe) {
                    authPreferences.saveAccessToken(response.token)
                }
            } catch (e: IOException) {
                throw IOException("Could not reach the server, please check your internet connection!")
            } catch (e: HttpException) {
                throw e.response()?.let { HttpException(it) }!!
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun autoLogin() {
            val accessToken = authPreferences.getAccessToken.first()

            if (accessToken.isEmpty()) {
                throw IllegalStateException("No access token found")
            }
        }

        override suspend fun logout() {
            try {
                authPreferences.clearAccessToken()
                val remainingToken = authPreferences.getAccessToken.first()

                if (remainingToken.isNotEmpty()) {
                    throw IllegalStateException("Failed to clear access token")
                }

                authPreferences.clearAccessToken()
            } catch (e: Exception) {
                throw e
            }
        }

        private suspend fun getAllUsers(email: String): UserResponseDto? =
            try {
                val users = authApiService.getAllUsers()
                users.find { it.email == email }
            } catch (e: Exception) {
                null
            }
    }
