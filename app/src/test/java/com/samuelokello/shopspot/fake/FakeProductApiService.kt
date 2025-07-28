package com.samuelokello.shopspot.fake

import com.samuelokello.shopspot.data.network.product.ProductApiService
import com.samuelokello.shopspot.data.network.product.dto.ProductDto

class FakeProductApiService : ProductApiService {
    override suspend fun getProducts(): List<ProductDto> = FakeDataSource.productApiList

    override suspend fun getCategories(): List<String> {
        TODO("Not yet implemented")
    }
}