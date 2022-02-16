package com.itomych.arch.model.storage.impl

import android.content.SharedPreferences
import com.itomych.arch.model.storage.Storage

class StorageImpl(private val prefs: SharedPreferences) : Storage {
    override suspend fun saveToken(token: String) {

    }

    override suspend fun getToken(): String? {
        return "stub"
    }
}