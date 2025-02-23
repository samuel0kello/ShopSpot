package com.samuelokello.auth.di

import com.samuelokello.auth.login.LoginViewModel
import com.samuelokello.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    // Provide loginViewmodel
    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel =
        LoginViewModel(
            loginUseCase = loginUseCase,
        )
}
