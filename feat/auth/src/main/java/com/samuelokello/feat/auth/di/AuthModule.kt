package com.samuelokello.feat.auth.di

import com.samuelokello.feat.auth.presentation.login.LoginViewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule =
    module {
        viewModelOf(::LoginViewModel)
    }
