package com.samuelokello.network.auth

import com.samuelokello.network.auth.dto.UserResponseDto
import com.samuelokello.network.auth.response.LoginResponse
import com.samuelokello.shopspot.data.network.auth.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest,
    ): LoginResponse

    @GET("users/")
    suspend fun getAllUsers(): List<UserResponseDto>
}
