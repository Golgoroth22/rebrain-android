package com.falin.valentin.foodapp.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.falin.valentin.foodapp.utils.Logger
import timber.log.Timber

/**
 * Parent class-activity with Timber logging in lifecycle methods.
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract val owner: Logger.Owner
    private val logger = Logger(lifecycle, owner)
}