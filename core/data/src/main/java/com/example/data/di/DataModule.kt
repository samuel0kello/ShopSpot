package com.example.data.di

import com.example.core.domain.repository.AuthenticationRepository
import com.example.data.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module
import kotlin.math.sin

val dataModule =
    module {
        single<AuthenticationRepository> {
            AuthenticationRepositoryImpl(
                localSource = get(),
                remoteSource = get(),
            )
        }
    }