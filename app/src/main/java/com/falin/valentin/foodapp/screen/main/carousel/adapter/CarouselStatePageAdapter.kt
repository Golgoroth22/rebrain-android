package com.falin.valentin.foodapp.screen.main.carousel.adapter

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.falin.valentin.foodapp.screen.main.carousel.CarouselFragment

/**
 * [FragmentStatePagerAdapter] subclass to work with CarouselStatePageAdapter.
 *
 */
class CarouselStatePageAdapter(fm: FragmentManager, private val picturesList: List<Uri>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return CarouselFragment.newInstance(picturesList[position])
    }

    override fun getCount(): Int {
        return picturesList.size
    }
}