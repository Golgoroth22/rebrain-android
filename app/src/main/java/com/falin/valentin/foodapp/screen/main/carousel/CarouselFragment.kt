package com.falin.valentin.foodapp.screen.main.carousel

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide

import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger
import kotlinx.android.synthetic.main.fragment_carousel.view.carousel_image_view

/**
 * [Fragment] subclass to work with CarouselFragment and showing it.
 *
 */
class CarouselFragment : BaseFragment() {
    override val owner: Logger.Owner
        get() = Logger.Owner.CAROUSEL_FRAGMENT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_carousel, container, false)
        arguments?.getString(IMG_URI)?.let {
            Glide.with(context!!)
                .load(it)
                .thumbnail(0.25f)
                .into(rootView.carousel_image_view)
        }
        return rootView
    }

    companion object {
        fun newInstance(uri: Uri): CarouselFragment {
            val fragment = CarouselFragment()
            fragment.arguments = bundleOf(IMG_URI to uri.toString())
            return fragment
        }

        private const val IMG_URI = "image_uri"
    }
}