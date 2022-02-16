package com.itomych.arch.view.splash

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.itomych.arch.GraphRootDirections
import com.itomych.arch.R
import com.itomych.arch.view.base.BaseFlowFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashFlowFragment : BaseFlowFragment<SplashNavigation>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation.destinations.onEach {
            when (it) {
                SplashNavigation.AuthFlow -> findNavController().navigate(GraphRootDirections.startFlowAuth())
            }
        }.launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SPLASH_DESTROY", "SPLASH_DESTROY")
    }

    override val layoutId: Int
        get() = R.layout.fragment_root_flow_container_splash
}