package com.itomych.arch.model.api.model.request

data class SignInRequest(val login: String, val password: String, val code: String)
