package com.falin.valentin.foodapp.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

/**
 * Parent class-activity with Timber logging in lifecycle methods.
 *
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}