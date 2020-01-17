package com.falin.valentin.foodapp.screen.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.RebrainApp
import com.falin.valentin.foodapp.di.module.IntroDisplayStorageModule
import com.falin.valentin.foodapp.di.module.IntroViewModelFactoryModule
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.injectViewModel
import com.falin.valentin.foodapp.viewmodel.IntroViewModel
import com.falin.valentin.foodapp.viewmodel.IntroViewModelFactory
import kotlinx.android.synthetic.main.activity_intro.*
import javax.inject.Inject

/**
 * Class-activity for work with IntroActivity and showing it.
 */
class IntroActivity : BaseActivity() {
    override val owner: Logger.Owner
        get() = Logger.Owner.INTRO_ACTIVITY

    @Inject
    lateinit var viewModelFactory: IntroViewModelFactory
    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        setContentView(R.layout.activity_intro)
        introViewModel = injectViewModel(viewModelFactory)
        checkIsIntroActivityViewed()
        intro_root_layout.setOnClickListener {
            MainActivity.start(this)
            finishAffinity()
        }
    }

    private fun initDagger() {
        RebrainApp.DAGGER.initIntroComponent(
            IntroDisplayStorageModule(),
            IntroViewModelFactoryModule()
        ).inject(this)
    }

    private fun checkIsIntroActivityViewed() {
        introViewModel.setIntroShowed()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, IntroActivity::class.java))
        }
    }
}
