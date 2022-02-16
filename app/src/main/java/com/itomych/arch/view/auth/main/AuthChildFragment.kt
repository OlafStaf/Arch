package com.itomych.arch.view.auth.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itomych.arch.databinding.FragmentFlowAuthMainBinding
import com.itomych.arch.view.auth.AuthNavigation
import com.itomych.arch.view.base.BaseChildFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthChildFragment : BaseChildFragment<AuthNavigation>() {

    var binding: FragmentFlowAuthMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowAuthMainBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
            btnLogIn.setOnClickListener {
                navigation.open(AuthNavigation.LoginScreen)
            }
            btnSignUp.setOnClickListener {
                navigation.open(AuthNavigation.SignUpScreen)
            }
        }
    }
}