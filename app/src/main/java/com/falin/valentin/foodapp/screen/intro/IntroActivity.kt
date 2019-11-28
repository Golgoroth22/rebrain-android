package com.falin.valentin.foodapp.screen.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.di.component.DaggerAppComponent
import com.falin.valentin.foodapp.di.module.AppModule
import com.falin.valentin.foodapp.interactor.IntroDisplayStorage
import com.falin.valentin.foodapp.repository.IntroInfoRepository
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.PreferencesHelper
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

    private lateinit var introViewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        introViewModel = ViewModelProviders.of(
            this,
            DaggerAppComponent.builder().appModule(AppModule(this)).build().introViewModelFactory()
        ).get(IntroViewModel::class.java)
        checkIsIntroActivityViewed()
        intro_root_layout.setOnClickListener {
            MainActivity.start(this)
            finishAffinity()
        }
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
