package com.samuelokello.domain.usecases

import com.samuelokello.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase
    @Inject
    constructor(
        private val loginRepository: LoginRepository,
    ) {
        suspend operator fun invoke(
            username: String,
            password: String,
            rememberMe: Boolean,
        ) {
            loginRepository.login(username, password, rememberMe)
        }
    }
