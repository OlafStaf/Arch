package com.itomych.arch.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.itomych.arch.R
import com.itomych.arch.databinding.FragmentFlowSplashBinding
import com.itomych.arch.view.base.BaseChildFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SplashChildFragment : BaseChildFragment<SplashNavigation>() {
    private val viewModel: SplashViewModel by provideSharedViewModel()

    var binding: FragmentFlowSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowSplashBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getState().onEach {
            with(binding!!) {
                when (it) {
                    SplashViewModel.ViewStates.Loading -> {
                        tvState.text = it.name
                    }
                    SplashViewModel.ViewStates.Authorized -> {
                        navigation.open(SplashNavigation.AuthFlow)
                    }
                    SplashViewModel.ViewStates.Unauthorized -> navigation.open(SplashNavigation.AuthFlow)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}