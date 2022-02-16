package com.itomych.arch.view.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itomych.arch.databinding.FragmentFlowAuthLogInBinding
import com.itomych.arch.view.auth.AuthNavigation
import com.itomych.arch.view.base.BaseChildFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginChildFragment : BaseChildFragment<AuthNavigation>() {

    var binding: FragmentFlowAuthLogInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowAuthLogInBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}