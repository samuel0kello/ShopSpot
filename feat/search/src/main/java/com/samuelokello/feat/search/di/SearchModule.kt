package com.samuelokello.feat.search.di

import com.samuelokello.feat.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule =
    module { viewModelOf(::SearchViewModel) }
