package com.itomych.arch.di.module

import com.itomych.arch.model.repository.AuthRepository
import com.itomych.arch.model.repository.UserRepository
import com.itomych.arch.model.usecase.GetUserAuthStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetAuthStateUseCase(authRepository: AuthRepository, userRepository: UserRepository): GetUserAuthStateUseCase {
        return GetUserAuthStateUseCase(authRepository, userRepository)
    }
}