package com.itomych.arch.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.itomych.arch.R

abstract class BaseFlowFragment<T : Navigation> : Fragment() {
    abstract val layoutId: Int

    protected val localNavController: NavController by lazy {
        (childFragmentManager.findFragmentById(R.id.localNavControllerContainer) as NavHostFragment).navController
    }
    protected val navigation: NavigationViewModel<T> by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val previousBackStackEntry = localNavController.previousBackStackEntry
                if (previousBackStackEntry == null) {
                    OnBackPressedCallback@ this.isEnabled = false
                    requireActivity().onBackPressed()
                } else {
                    localNavController.navigateUp()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}