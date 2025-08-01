package com.example.remote.util

import com.example.core.domain.repository.AuthenticationRepository
import com.example.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(authenticationRepository: AuthenticationRepository): HttpClient =
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }
            install(Logging) {
                logger = io.ktor.client.plugins.logging.Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = authenticationRepository.getAccessToken().firstOrNull()
                        val refreshToken = authenticationRepository.getRefreshToken().firstOrNull()

                        if (accessToken != null && refreshToken != null) {
                            BearerTokens(accessToken, refreshToken)
                        } else {
                            null
                        }
                    }

                    refreshTokens {
                        val newTokens = authenticationRepository.performTokenRefresh()

                        if (newTokens is Result.Success) {
                            BearerTokens(newTokens.data.accessToken, newTokens.data.refreshToken)
                        } else {
                            authenticationRepository.clearTokens()
                            null
                        }
                    }
                }
            }
        }
}