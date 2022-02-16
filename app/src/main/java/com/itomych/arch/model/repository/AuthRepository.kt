package com.itomych.arch.model.repository

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.model.response.AuthResponse
import com.itomych.arch.model.api.model.response.UserResponse

interface AuthRepository {
    suspend fun signIn(username:String, password: String, code: String) : ApiResponse<AuthResponse, Unit>
}