package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.screen.main.carousel.adapter.CarouselStatePageAdapter
import kotlinx.android.synthetic.main.fragment_main_tab.*

/**
 * [Fragment] subclass to work with MainTabFragment and showing it.
 *
 */
class MainTabFragment : BaseFragment() {
    companion object {
        fun newInstance(): MainTabFragment {
            return MainTabFragment()
        }
    }

    private lateinit var pageAdapter: CarouselStatePageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        pageAdapter = CarouselStatePageAdapter(childFragmentManager, picList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_fragment_tab_pager.adapter = pageAdapter
    }
}