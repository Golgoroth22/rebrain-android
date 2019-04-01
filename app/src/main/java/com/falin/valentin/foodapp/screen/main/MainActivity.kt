package com.falin.valentin.foodapp.screen.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var pageAdapter: CarouselStatePageAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picList = listOf(
            R.drawable.food_1,
            R.drawable.food_2,
            R.drawable.food_3,
            R.drawable.food_4,
            R.drawable.food_5,
            R.drawable.food_6,
            R.drawable.food_7,
            R.drawable.food_8,
            R.drawable.food_9,
            R.drawable.food_10
        )
        pageAdapter = CarouselStatePageAdapter(supportFragmentManager, picList)
        viewPager = findViewById(R.id.main_view_pager)
        viewPager.adapter = pageAdapter
    }
}
