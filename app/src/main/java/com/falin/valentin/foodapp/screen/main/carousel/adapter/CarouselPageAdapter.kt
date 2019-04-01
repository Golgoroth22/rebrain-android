package com.falin.valentin.foodapp.screen.main.carousel.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.falin.valentin.foodapp.screen.main.carousel.CarouselFragment

/**
 * [FragmentPagerAdapter] subclass to work with CarouselPageAdapter.
 *
 */
class CarouselPageAdapter(fm: FragmentManager, private val picturesList: List<Int>) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return CarouselFragment.newInstance(picturesList[p0])
    }

    override fun getCount(): Int {
        return picturesList.size
    }
}