package com.falin.valentin.foodapp.screen.main.carousel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.falin.valentin.foodapp.screen.main.carousel.CarouselFragment

/**
 * [FragmentStatePagerAdapter] subclass to work with CarouselStatePageAdapter.
 *
 */
class CarouselStatePageAdapter(fm: FragmentManager, private val picturesList: List<Int>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(pictureId: Int): Fragment {
        return CarouselFragment.newInstance(picturesList[pictureId])
    }

    override fun getCount(): Int {
        return picturesList.size
    }
}