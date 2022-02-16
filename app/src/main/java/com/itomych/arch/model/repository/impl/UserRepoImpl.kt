package com.itomych.arch.model.repository.impl

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.client.AppApi
import com.itomych.arch.model.api.model.response.PortfolioResponse
import com.itomych.arch.model.api.model.response.UserResponse
import com.itomych.arch.model.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepoImpl(
    private val api: AppApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : UserRepository {

    override suspend fun getUserInfo(): ApiResponse<UserResponse, Unit> =
        withContext(defaultDispatcher) {
            api.getUserInfo()
        }

    override suspend fun getUserPortfolio(): ApiResponse<PortfolioResponse, Unit> =
        withContext(defaultDispatcher) {
            api.getPortfolio()
        }
}