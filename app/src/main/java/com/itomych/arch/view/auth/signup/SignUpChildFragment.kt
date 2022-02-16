package com.itomych.arch.view.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itomych.arch.databinding.FragmentFlowAuthSignUpBinding
import com.itomych.arch.view.auth.AuthNavigation
import com.itomych.arch.view.base.BaseChildFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpChildFragment : BaseChildFragment<AuthNavigation>() {

    var binding: FragmentFlowAuthSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowAuthSignUpBinding.inflate(inflater, container, false)
        binding!!.btnOpenLogin.setOnClickListener {
            navigation.open(AuthNavigation.LoginScreen)
        }
        return binding!!.root
    }
}