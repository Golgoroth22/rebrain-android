package com.falin.valentin.foodapp.screen.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.main.MainActivity
import com.falin.valentin.foodapp.utils.Logger
import com.falin.valentin.foodapp.utils.PreferencesHelper
import kotlinx.android.synthetic.main.activity_intro.*

/**
 * Class-activity for work with IntroActivity and showing it.
 */
class IntroActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, IntroActivity::class.java))
        }
    }

    private val logger = Logger(lifecycle, Logger.Owner.INTRO_ACTIVITY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        checkIsIntroActivityViewed()
        intro_root_layout.setOnClickListener {
            MainActivity.start(this)
            finishAffinity()
        }
    }

    private fun checkIsIntroActivityViewed() {
        if (!PreferencesHelper(this).introInfo) {
            PreferencesHelper(this).introInfo = true
        }
    }
}
