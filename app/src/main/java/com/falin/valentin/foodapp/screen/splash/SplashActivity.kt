package com.falin.valentin.foodapp.screen.splash

import android.os.Bundle
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.utils.PreferencesHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Class-activity for work with SplashActivity and showing it.
 */
class SplashActivity : BaseActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        waitABitAndGoNext()
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
