package com.falin.valentin.foodapp.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber


/**
 * [LifecycleObserver] subclass logging lifecycle.
 *
 */
class Logger(lifecycle: Lifecycle, private val owner: Owner) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Timber.i("$owner onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.i("$owner onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Timber.i("$owner onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.i("$owner onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Timber.i("$owner onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Timber.i("$owner onDestroy")
    }

    init {
        lifecycle.addObserver(this)
    }

    enum class Owner {
        MAIN_ACTIVITY, INTRO_ACTIVITY, SPLASH_ACTIVITY,
        ACCOUNT_TAB_FRAGMENT, MAIN_TAB_FRAGMENT, FAVORITE_TAB_FRAGMENT, CAROUSEL_FRAGMENT
    }
}