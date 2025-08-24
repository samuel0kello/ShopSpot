package com.samuelokello.core.presentation.ui.di

import com.samuelokello.feat.auth.di.authModule
import com.samuelokello.feat.cart.di.cartModule
import com.samuelokello.feat.favourite.favouritesModule
import com.samuelokello.feat.home.di.homeModule
import com.samuelokello.feat.product.di.productModule
import com.samuelokello.feat.profile.di.profileModule
import com.samuelokello.feat.search.di.searchModule
import org.koin.core.annotation.KoinInternalApi
import org.koin.dsl.module

val featureModules =
    listOf(
        authModule,
        homeModule,
        cartModule,
        productModule,
        searchModule,
        profileModule,
        favouritesModule,
    )

@OptIn(KoinInternalApi::class)
val uiModule =
    module {
        includes(featureModules)
    }
