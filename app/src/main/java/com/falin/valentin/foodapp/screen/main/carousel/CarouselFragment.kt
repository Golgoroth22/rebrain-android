package com.falin.valentin.foodapp.screen.main.carousel


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import kotlinx.android.synthetic.main.fragment_carousel.view.*

private const val IMG_ID = "image_id"

/**
 * [Fragment] subclass to work with CarouselFragment and showing it.
 *
 */
class CarouselFragment : BaseFragment() {
    companion object {
        fun newInstance(imageId: Int): CarouselFragment {
            val fragment = CarouselFragment()
            fragment.arguments = bundleOf(IMG_ID to imageId)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_carousel, container, false)
        arguments?.getInt(IMG_ID)?.let { rootView.carousel_image_view.setImageResource(it) }
        return rootView
    }

}
