package com.falin.valentin.foodapp.screen.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.MainActivity
import com.falin.valentin.foodapp.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {
    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_splash)

        waitABitAndGoNext()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun getSplashScreenDuration() = 5000L

    private fun waitABitAndGoNext() = launch {
        delay(getSplashScreenDuration())
        MainActivity.start(applicationContext)
        finish()
    }
}
