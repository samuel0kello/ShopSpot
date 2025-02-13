package com.samuelokello.network.product

import com.samuelokello.network.product.dto.ProductDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
    @GET("Category")
    suspend fun getCategories(): List<String>
}