package com.samuelokello.repository.di

import com.samuelokello.core.preference.AuthPreferences
import com.samuelokello.domain.repository.CartRepository
import com.samuelokello.domain.repository.LoginRepository
import com.samuelokello.domain.repository.ProductRepository
import com.samuelokello.local.dao.ProductDao
import com.samuelokello.local.dao.UserCartDao
import com.samuelokello.network.auth.AuthApiService
import com.samuelokello.network.cart.CartApiService
import com.samuelokello.network.product.ProductApiService
import com.samuelokello.repository.CartRepositoryImpl
import com.samuelokello.repository.LoginRepositoryImpl
import com.samuelokello.repository.ProductRepositoryImpl
import com.samuelokello.repository.mapper.ProductApiMapper
import com.samuelokello.repository.mapper.ProductEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCartRepository(
        cartApiService: CartApiService,
        userCartDao: UserCartDao,
    ): CartRepository = CartRepositoryImpl(cartApiService, userCartDao)

    @Provides
    @Singleton
    fun provideLoginRepository(
        authApiService: AuthApiService,
        authPreferences: AuthPreferences,
    ): LoginRepository = LoginRepositoryImpl(authApiService, authPreferences)

    @Provides
    @Singleton
    fun provideProductRepository(
        productApiService: ProductApiService,
        productDao: ProductDao,
        productApiMapper: ProductApiMapper,
        productEntityMapper: ProductEntityMapper,
    ): ProductRepository =
        ProductRepositoryImpl(
            productApiService,
            productDao,
            productApiMapper,
            productEntityMapper,
        )
}
