package com.falin.valentin.foodapp.screen.intro

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.utils.PreferencesHelper
import timber.log.Timber

/**
 * Class-activity for work with IntroActivity and showing it.
 */
class IntroActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, IntroActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("Intro onCreate")
        setContentView(R.layout.activity_intro)
        checkIsIntroActivityViewed()
    }

    override fun onStart() {
        super.onStart()
        Timber.e("Intro onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.e("Intro onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.e("Intro onRestart")
    }

    override fun onPause() {
        super.onPause()
        Timber.e("Intro onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("Intro onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("Intro onDestroy")
    }

    private fun checkIsIntroActivityViewed() {
        if (!PreferencesHelper(this).introInfo) {
            PreferencesHelper(this).introInfo = true
        }
    }
}
