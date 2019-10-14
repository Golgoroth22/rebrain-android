package com.falin.valentin.foodapp.screen

import androidx.fragment.app.Fragment
import com.falin.valentin.foodapp.utils.Logger

/**
 * Parent class-fragment with Timber logging in lifecycle methods.
 *
 */
abstract class BaseFragment : Fragment() {

    abstract val owner: Logger.Owner

    private val logger = Logger(lifecycle, owner)
}