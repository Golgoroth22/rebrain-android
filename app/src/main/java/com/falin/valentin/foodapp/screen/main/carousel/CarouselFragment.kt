package com.falin.valentin.foodapp.screen.main.carousel


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.falin.valentin.foodapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * [Fragment] subclass to work with CarouselFragment and showing it.
 *
 */
class CarouselFragment : Fragment() {
    companion object {
        fun newInstance(): Fragment {
            val bundle = Bundle()
            val fragment = CarouselFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carousel, container, false)
    }

}
