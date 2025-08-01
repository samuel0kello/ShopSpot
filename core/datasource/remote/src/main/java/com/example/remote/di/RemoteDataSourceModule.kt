package com.example.remote.di

import com.example.remote.util.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.dsl.module

val remoteDataSourceModule =
    module {
        single <HttpClient>{
            HttpClientFactory.create()
        }
    }