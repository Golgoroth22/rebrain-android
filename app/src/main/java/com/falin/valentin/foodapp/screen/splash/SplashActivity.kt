package com.falin.valentin.foodapp.screen.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.MainActivity
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.utils.PreferencesHelper
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Class-activity for work with SplashActivity and showing it.
 */
class SplashActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.e("Splash onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        waitABitAndGoNext()
    }

    override fun onStart() {
        Timber.e("Splash onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.e("Splash onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.e("Splash onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.e("Splash onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.e("Splash onDestroy")
        super.onDestroy()
    }

    private fun getSplashScreenDuration() = 500L

    private fun waitABitAndGoNext() = launch {
        delay(getSplashScreenDuration())
        loadIntroOrMainActivity()
    }

    private fun loadIntroOrMainActivity() {
        val prefHelper = PreferencesHelper(this)
        if (prefHelper.introInfo) {
            MainActivity.start(this)
        } else {
            IntroActivity.start(this)
        }
        finish()
    }
}
