package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.custom_view.MainTabType
import com.falin.valentin.foodapp.screen.custom_view.onClickCustomListener
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
        main_activity_view_pager.adapter = pageAdapter

        main_activity_custom_bottom_bar.setOnCustomClickListener(object : onClickCustomListener {
            override fun onClick(tabType: MainTabType) {
                when (tabType) {
                    MainTabType.MAIN -> selectMainTabFragment()
                    MainTabType.FAVORITE -> selectFavoriteTabFragment()
                }
            }
        })
    }

    private fun selectFavoriteTabFragment() {
        main_activity_view_pager.currentItem = 1
    }

    private fun selectMainTabFragment() {
        main_activity_view_pager.currentItem = 0
    }
}