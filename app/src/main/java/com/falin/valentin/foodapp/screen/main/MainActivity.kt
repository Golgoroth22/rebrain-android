package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseActivity
import com.falin.valentin.foodapp.screen.main.carousel.adapter.MainPageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorite_tab.view.*
import kotlinx.android.synthetic.main.layout_custom_bottom_bar.*
import kotlinx.android.synthetic.main.layout_custom_button.view.*

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
        makeMainButtonGreen()

        custom_bottom_bar_main_button.setOnClickListener {
            main_activity_view_pager.currentItem = main_activity_view_pager.currentItem - 1
            makeFavoriteButtonGrey()
            makeMainButtonGreen()
        }
        custom_bottom_bar_favorite_button.setOnClickListener {
            main_activity_view_pager.currentItem = main_activity_view_pager.currentItem + 1
            makeMainButtonGrey()
            makeFavoriteButtonGreen()
        }
    }

    private fun makeMainButtonGrey() {
        custom_bottom_bar_main_button.custom_button_text.setTextColor(resources.getColor(R.color.colorAppAccent))
        custom_bottom_bar_main_button.custom_button_image.setImageResource(R.drawable.main_off)
    }

    private fun makeMainButtonGreen() {
        custom_bottom_bar_main_button.custom_button_text.setTextColor(resources.getColor(R.color.colorCustomButtonText))
        custom_bottom_bar_main_button.custom_button_image.setImageResource(R.drawable.main_on)
    }

    private fun makeFavoriteButtonGrey() {
        custom_bottom_bar_favorite_button.custom_button_text.setTextColor(resources.getColor(R.color.colorAppAccent))
        custom_bottom_bar_favorite_button.custom_button_image.setImageResource(R.drawable.favorites_off)
    }

    private fun makeFavoriteButtonGreen() {
        custom_bottom_bar_favorite_button.custom_button_text.setTextColor(resources.getColor(R.color.colorCustomButtonText))
        custom_bottom_bar_favorite_button.custom_button_image.setImageResource(R.drawable.favorites_on)
    }
}