package com.samuelokello.shopspot.di

import com.samuelokello.shopspot.ShopSpotApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationModule =
    module {
        single<CoroutineScope> { (androidApplication() as ShopSpotApp).applicationScope }
    }