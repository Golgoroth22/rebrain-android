package com.falin.valentin.foodapp.screen.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
import com.falin.valentin.foodapp.di.module.AppModule
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.repository.IntroInfoRepository
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.PreferencesHelper
import com.falin.valentin.foodapp.viewmodel.IntroViewModel
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Class-activity for work with SplashActivity and showing it.
 */
class SplashActivity : BaseActivity(), CoroutineScope {
    override val owner: Logger.Owner
        get() = Logger.Owner.SPLASH_ACTIVITY
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        introViewModel = ViewModelProviders.of(
            this,
            DaggerAppComponent.builder().appModule(AppModule(this)).build().introViewModelFactory()
        ).get(IntroViewModel::class.java)
        waitABitAndGoNext()
    }

    private fun getSplashScreenDuration() = 500L

    private fun waitABitAndGoNext() = launch {
        delay(getSplashScreenDuration())
        loadIntroOrMainActivity()
    }

    private fun loadIntroOrMainActivity() {
        if (introViewModel.isIntroShowed()) {
            MainActivity.start(this)
        } else {
            IntroActivity.start(this)
        }
        finish()
    }
}
