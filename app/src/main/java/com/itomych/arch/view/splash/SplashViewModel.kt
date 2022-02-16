package com.itomych.arch.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itomych.arch.model.usecase.GetUserAuthStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val useCase: GetUserAuthStateUseCase) :
    ViewModel() {
    private val stateFlow: MutableStateFlow<ViewStates> = MutableStateFlow(ViewStates.Loading)

    fun getState(): StateFlow<ViewStates> {
        viewModelScope.launch {
            val state = when (val result = useCase("stub", "stub", "stub")) {
                is GetUserAuthStateUseCase.AuthState.Authorized -> ViewStates.Authorized
                GetUserAuthStateUseCase.AuthState.NotAuthorized -> ViewStates.Unauthorized
            }
            stateFlow.emit(state)
        }
        return stateFlow
    }

    enum class ViewStates {
        Loading, Authorized, Unauthorized
    }
}
