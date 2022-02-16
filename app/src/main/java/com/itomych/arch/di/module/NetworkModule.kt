package com.itomych.arch.di.module

import com.itomych.arch.model.api.client.AppApi
import com.itomych.arch.model.api.client.adapter.ApiResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://itc.ua"
    }

    @Provides
    fun provideOkHttpClient(@BaseUrl baseUrl: String): OkHttpClient {
        return OkHttpClient.Builder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                if (chain.request().url().toString() != baseUrl + "auth") {
//                    requestBuilder.addHeader("Authorization", pref.getToken())
                }
                val request = requestBuilder.build()
                val response = chain.proceed(request)
                response
            }
        }.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = ApiResponseAdapterFactory()

    @Provides
    @Singleton
    fun provideApi(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): AppApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build()
        return retrofit.create(AppApi::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl
}