package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite_tab.view.*
import kotlinx.android.synthetic.main.layout_custom_button.view.*

/**
 * [Fragment] subclass to work with FavoriteTabFragment and showing it.
 *
 */
class FavoriteTabFragment : BaseFragment() {
    companion object {
        fun newInstance(): FavoriteTabFragment {
            return FavoriteTabFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_tab, container, false)
        rootView.custom_button.custom_button_text.text = getString(R.string.custom_button_text_favorite)
        rootView.custom_button.custom_button_image.setImageResource(R.drawable.favorites_on)
        return rootView
    }
}