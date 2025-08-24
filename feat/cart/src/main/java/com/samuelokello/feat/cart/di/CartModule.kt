package com.samuelokello.feat.cart.di

import com.samuelokello.feat.cart.CartViewModel
import org.koin.dsl.module

val cartModule =
    module { single<CartViewModel> { CartViewModel(get(), get()) } }