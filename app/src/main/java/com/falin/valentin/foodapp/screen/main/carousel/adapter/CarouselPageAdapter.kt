package com.falin.valentin.foodapp.screen.main.carousel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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