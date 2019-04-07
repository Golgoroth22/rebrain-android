package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.main.carousel.adapter.MainPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * [BaseActivity] subclass to work with MainActivity our application and showing it.
 *
 */
class MainActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var pageAdapter: MainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pageAdapter = MainPageAdapter(supportFragmentManager)
        main_view_pager.adapter = pageAdapter
    }
}