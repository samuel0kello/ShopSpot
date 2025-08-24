package com.samuelokello.feat.product.di

import com.samuelokello.feat.product.ProductDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val productModule =
    module {
        viewModelOf(::ProductDetailViewModel)
    }