package com.falin.valentin.foodapp.screen.intro

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.utils.PreferencesHelper

class IntroActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, IntroActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (!PreferencesHelper(this).introInfo) PreferencesHelper(this).introInfo = true
    }
}
