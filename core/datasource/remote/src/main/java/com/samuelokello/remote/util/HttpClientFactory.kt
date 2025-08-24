package com.samuelokello.remote.util

import com.samuelokello.core.domain.repository.AuthenticationRepository
import com.samuelokello.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object HttpClientFactory : KoinComponent {
    private val authenticationRepository: AuthenticationRepository by inject()

    fun create(): HttpClient =
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
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        // Now authenticationRepository is available as a member of HttpClientFactory
                        // Note: .firstOrNull() is blocking if the flow doesn't emit immediately.
                        // Consider if these token calls should be truly suspend functions.
                        // For simplicity with Ktor's non-suspend loadTokens, runBlocking might be used
                        // but be mindful of its implications.
                        // A better Ktor Auth setup might involve a suspend based token storage.
                        val accessToken = runBlocking { authenticationRepository.getAccessToken().firstOrNull() }
                        val refreshToken = runBlocking { authenticationRepository.getRefreshToken().firstOrNull() }

                        if (accessToken != null && refreshToken != null) {
                            BearerTokens(accessToken, refreshToken)
                        } else {
                            null
                        }
                    }

                    refreshTokens {
                        // Similar here, authenticationRepository is available
                        // This block in Ktor IS a suspend function, so runBlocking isn't needed here.
                        val newTokens = authenticationRepository.performTokenRefresh() // This is suspend

                        if (newTokens is Result.Success) {
                            BearerTokens(newTokens.data.accessToken, newTokens.data.refreshToken)
                        } else {
                            authenticationRepository.clearTokens() // Assuming this is not suspend or handle appropriately
                            null
                        }
                    }
                }
            }
        }
}
