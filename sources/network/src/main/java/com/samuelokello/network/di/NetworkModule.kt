package com.samuelokello.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.samuelokello.network.auth.AuthApiService
import com.samuelokello.network.cart.CartApiService
import com.samuelokello.network.product.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json =
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
            }
        return Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideProductApiService(retrofit: Retrofit): ProductApiService = retrofit.create(ProductApiService::class.java)

    @Provides
    fun provideCartApiService(retrofit: Retrofit): CartApiService = retrofit.create(CartApiService::class.java)

    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)
}
