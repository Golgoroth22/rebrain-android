package com.falin.valentin.foodapp.screen.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.MainActivity
import com.falin.valentin.foodapp.R
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

    private fun getSplashScreenDuration() = 5000L

    private fun waitABitAndGoNext() = launch {
        delay(getSplashScreenDuration())
        MainActivity.start(applicationContext)
        finish()
    }
}
