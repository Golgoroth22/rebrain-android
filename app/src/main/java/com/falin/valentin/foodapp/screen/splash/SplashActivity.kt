package com.falin.valentin.foodapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        waitABitAndGoNext()
    }

    private fun getSplashScreenDuration() = 5000L

    private fun waitABitAndGoNext() {
        Timer("GoNext", false).schedule(getSplashScreenDuration()) { routeToSomePage() }
    }

    private fun routeToSomePage() {
        startActivity(MainActivityIntent())
    }
}
