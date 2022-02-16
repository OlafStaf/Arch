package com.itomych.arch.view.auth

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.itomych.arch.GraphAuthDirections
import com.itomych.arch.R
import com.itomych.arch.view.base.BaseFlowFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AuthFlowFragment : BaseFlowFragment<AuthNavigation>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation.destinations.onEach {
            when (it) {
                AuthNavigation.LoginScreen -> localNavController.navigate(GraphAuthDirections.openLogin())
                AuthNavigation.SignUpScreen -> localNavController.navigate(GraphAuthDirections.openSignup())
            }
        }.launchIn(lifecycleScope)
    }

    override val layoutId: Int
        get() = R.layout.fragment_root_flow_container_auth
}