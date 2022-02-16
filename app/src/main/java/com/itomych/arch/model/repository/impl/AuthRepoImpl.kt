package com.itomych.arch.model.repository.impl

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.client.AppApi
import com.itomych.arch.model.api.model.request.SignInRequest
import com.itomych.arch.model.api.model.response.AuthResponse
import com.itomych.arch.model.repository.AuthRepository
import com.itomych.arch.model.storage.Storage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepoImpl(
    private val api: AppApi,
    private val authStorage: Storage,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : AuthRepository {
    override suspend fun signIn(
        username: String,
        password: String,
        code: String
    ): ApiResponse<AuthResponse, Unit> = withContext(defaultDispatcher) {
        val result = api.signIn(SignInRequest(username, password, code))
        if (result is ApiResponse.Success) {
            authStorage.saveToken(result.body.token)
        }
        result
    }
}