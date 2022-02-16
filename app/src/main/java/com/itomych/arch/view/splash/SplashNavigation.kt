package com.itomych.arch.view.splash

import com.itomych.arch.view.base.Navigation

sealed class SplashNavigation : Navigation {
    object AuthFlow : SplashNavigation()
}
