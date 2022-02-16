package com.itomych.arch.model.storage

interface Storage {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}