package com.falin.valentin.foodapp.screen.main.carousel.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.falin.valentin.foodapp.screen.main.carousel.CarouselFragment

/**
 * [FragmentStatePagerAdapter] subclass to work with CarouselStatePageAdapter.
 *
 */
class CarouselStatePageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val picturesList: ArrayList<Int> = ArrayList()

    override fun getItem(p0: Int): Fragment {
        return CarouselFragment.newInstance(picturesList[p0])
    }

    override fun getCount(): Int {
        return picturesList.size
    }

    fun addPicturesList(list: List<Int>) {
        picturesList.addAll(list)
    }
}