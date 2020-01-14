package com.falin.valentin.foodapp

import android.app.Application
import timber.log.Timber

/**
 *  [Application] subclass to work with our app.
 *
 */
class RebrainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
