package com.itomych.arch.model.api.client

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.model.request.SignInRequest
import com.itomych.arch.model.api.model.response.AuthResponse
import com.itomych.arch.model.api.model.response.PortfolioResponse
import com.itomych.arch.model.api.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

typealias GenericResponse<S> = ApiResponse<S, Unit>

interface AppApi {
    @POST("/auth/login")
    suspend fun signIn(@Body body: SignInRequest): GenericResponse<AuthResponse>

    @GET("/user/info")
    suspend fun getUserInfo(): GenericResponse<UserResponse>

    @GET("/user/portfolio")
    suspend fun getPortfolio(): GenericResponse<PortfolioResponse>
}