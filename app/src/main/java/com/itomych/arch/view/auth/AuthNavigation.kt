package com.itomych.arch.view.auth

import com.itomych.arch.view.base.Navigation

sealed class AuthNavigation : Navigation {
    object LoginScreen : AuthNavigation()
    object SignUpScreen : AuthNavigation()
}
