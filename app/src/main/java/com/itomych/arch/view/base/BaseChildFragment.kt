package com.itomych.arch.view.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel

abstract class BaseChildFragment<T : Navigation> : Fragment() {
    protected val navigation: NavigationViewModel<T> by provideSharedViewModel()

    protected inline fun <reified T : ViewModel> provideSharedViewModel(): Lazy<T> {
        return viewModels(ownerProducer = { requireParentFragment().requireParentFragment() })
    }
}