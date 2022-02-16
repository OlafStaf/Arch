package com.itomych.arch.model.usecase

import com.itomych.arch.model.api.ApiResponse
import com.itomych.arch.model.api.model.response.PortfolioResponse
import com.itomych.arch.model.api.model.response.UserResponse
import com.itomych.arch.model.repository.AuthRepository
import com.itomych.arch.model.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetUserAuthStateUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(login: String, password: String, code: String): AuthState {
        return when (authRepository.signIn(login, password, code)) {
            is ApiResponse.Error -> AuthState.NotAuthorized
            is ApiResponse.Success -> {
                coroutineScope {
                    val userInfoDeferred = async {
                        userRepository.getUserInfo()
                    }
                    val userPortfolioDeferred = async {
                        userRepository.getUserPortfolio()
                    }
                    (userInfoDeferred.await() to userPortfolioDeferred.await())
                        .takeIf { it.first is ApiResponse.Success && it.second is ApiResponse.Success }
                        ?.let {
                            AuthState.Authorized(
                                (it.first as ApiResponse.Success).body,
                                (it.second as ApiResponse.Success).body
                            )
                        } ?: AuthState.NotAuthorized
                }
            }
        }
    }

    sealed class AuthState {
        data class Authorized(val user: UserResponse, val portfolio: PortfolioResponse) :
            AuthState()
        object NotAuthorized : AuthState()
    }
}