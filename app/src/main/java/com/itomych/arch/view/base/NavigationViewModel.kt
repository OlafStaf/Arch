package com.itomych.arch.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel<T : Navigation> @Inject constructor() : ViewModel() {
    private val navigationFlow: MutableSharedFlow<T> = MutableSharedFlow(replay = 0)
    val destinations: SharedFlow<T>
        get() = navigationFlow

    fun open(destination: T) {
        viewModelScope.launch {
            navigationFlow.emit(destination)
        }
    }
}