package com.falin.valentin.foodapp

import android.app.Application
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
import com.falin.valentin.foodapp.di.component.ScreenComponent
import com.falin.valentin.foodapp.di.module.*
import timber.log.Timber

/**
 *  [Application] subclass to work with our app.
 *
 * @property DAGGER property for DI
 */
class RebrainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        DAGGER = initScreenComponent()
    }

    private fun initScreenComponent() =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
            .initScreenComponent(
                IntroDisplayStorageModule(),
                IntroViewModelFactoryModule(),
                ProductListViewModelFactoryModule(),
                ProductModeStorageModule()
            )

    companion object {
        lateinit var DAGGER: ScreenComponent
            private set
    }
}
