package com.samuelokello.feat.home.di

import com.samuelokello.feat.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule =
    module {
        viewModelOf(::HomeViewModel)
    }