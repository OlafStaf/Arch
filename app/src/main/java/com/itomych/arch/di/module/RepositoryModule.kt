package com.itomych.arch.di.module

import com.itomych.arch.model.api.client.AppApi
import com.itomych.arch.model.repository.AuthRepository
import com.itomych.arch.model.repository.UserRepository
import com.itomych.arch.model.repository.impl.AuthRepoImpl
import com.itomych.arch.model.repository.impl.UserRepoImpl
import com.itomych.arch.model.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAuthRepository(
        api: AppApi,
        authStorage: Storage,
        defaultDispatcher: CoroutineDispatcher
    ): AuthRepository {
        return AuthRepoImpl(api, authStorage, defaultDispatcher)
    }

    @Provides
    fun provideUserRepository(
        api: AppApi,
        defaultDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepoImpl(api, defaultDispatcher)
    }

    @Provides
    fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
