package com.falin.valentin.foodapp.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

/**
 * Parent class-fragment with Timber logging in lifecycle methods.
 *
 */
open class BaseFragment : Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.i("${activity?.localClassName} onAttach called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("${activity?.localClassName} onCreate called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("${activity?.localClassName} onCreateView called")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i("${activity?.localClassName} onActivityCreated called")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("${activity?.localClassName} onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("${activity?.localClassName} onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("${activity?.localClassName} onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("${activity?.localClassName} onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("${activity?.localClassName} onDestroyView called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("${activity?.localClassName} onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("${activity?.localClassName} onDetach called")
    }
}