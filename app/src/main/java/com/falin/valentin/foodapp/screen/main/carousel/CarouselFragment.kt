package com.falin.valentin.foodapp.screen.main.carousel


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import kotlinx.android.synthetic.main.fragment_carousel.view.*

private const val ARG_PARAM = "param"

/**
 * [Fragment] subclass to work with CarouselFragment and showing it.
 *
 */
class CarouselFragment : BaseFragment() {
    companion object {
        fun newInstance(imageId: Int): CarouselFragment {
            val bundle = Bundle()
            bundle.putInt(ARG_PARAM, imageId)
            val fragment = CarouselFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_carousel, container, false)
        arguments?.getInt(ARG_PARAM)?.let { rootView.carousel_image_view.setImageResource(it) }
        return rootView
    }

}
