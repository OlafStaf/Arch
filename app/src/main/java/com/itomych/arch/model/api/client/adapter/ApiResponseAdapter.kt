package com.itomych.arch.model.api.client.adapter

import com.itomych.arch.model.api.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class ApiResponseAdapter<T : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<T, Call<ApiResponse<T, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ApiResponse<T, E>> {
        return ApiResponseCall(call, errorBodyConverter)
    }
}
