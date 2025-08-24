package com.samuelokello.data.repository.di

import com.samuelokello.core.domain.repository.AuthenticationRepository
import com.samuelokello.data.repository.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val dataModule =
    module {
        single<AuthenticationRepository> {
            AuthenticationRepositoryImpl(
                localSource = get(),
                remoteSource = get(),
            )
        }
    }