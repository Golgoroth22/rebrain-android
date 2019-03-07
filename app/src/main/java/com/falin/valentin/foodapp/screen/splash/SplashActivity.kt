package com.falin.valentin.foodapp.screen.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.MainActivity
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.utils.PreferencesHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {
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
            prefHelper.introInfo = true
            IntroActivity.start(this)
        }
        finish()
    }
}
