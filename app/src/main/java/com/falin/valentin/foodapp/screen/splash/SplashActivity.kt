package com.falin.valentin.foodapp.screen.splash

import android.os.Bundle
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.*
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.intro.IntroActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.IntroViewModel
import com.falin.valentin.foodapp.viewmodel.factories.IntroViewModelFactory
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

    @Inject
    lateinit var viewModelFactory: IntroViewModelFactory
    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(R.layout.activity_splash)
        introViewModel = injectViewModel(viewModelFactory)
        waitABitAndGoNext()
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initSplashComponent(
            IntroDisplayStorageModule(),
            IntroViewModelFactoryModule()
        ).inject(this)
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
