package com.itomych.arch.model.repository

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.model.response.PortfolioResponse
import com.itomych.arch.model.api.model.response.UserResponse

interface UserRepository {
    suspend fun getUserInfo(): ApiResponse<UserResponse, Unit>
    suspend fun getUserPortfolio(): ApiResponse<PortfolioResponse, Unit>
}