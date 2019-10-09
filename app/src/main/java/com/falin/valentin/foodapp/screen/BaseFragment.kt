package com.falin.valentin.foodapp.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.falin.valentin.foodapp.utils.Logger
import timber.log.Timber

/**
 * Parent class-fragment with Timber logging in lifecycle methods.
 *
 */
abstract class BaseFragment : Fragment() {

    abstract val owner: Logger.Owner
    private val logger = Logger(lifecycle, owner)
}