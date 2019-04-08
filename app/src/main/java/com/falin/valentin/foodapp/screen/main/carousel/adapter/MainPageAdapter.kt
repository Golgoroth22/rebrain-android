package com.falin.valentin.foodapp.screen.main.carousel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.falin.valentin.foodapp.screen.main.FavoriteTabFragment
import com.falin.valentin.foodapp.screen.main.MainTabFragment

/**
 * [FragmentPagerAdapter] subclass to work with MainPageAdapter.
 *
 */
class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragmentsList = listOf(
        MainTabFragment.newInstance(),
        FavoriteTabFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment {
        return fragmentsList[position]
    }

    override fun getCount(): Int {
        return fragmentsList.size
    }
}