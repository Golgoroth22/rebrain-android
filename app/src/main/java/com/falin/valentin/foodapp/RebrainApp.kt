package com.falin.valentin.foodapp

import android.app.Application
import com.falin.valentin.foodapp.di.component.AppComponent
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
import com.falin.valentin.foodapp.di.module.AppModule
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
        DAGGER = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var DAGGER: AppComponent
    }
}
