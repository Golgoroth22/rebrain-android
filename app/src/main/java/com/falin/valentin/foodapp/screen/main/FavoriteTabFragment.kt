package com.falin.valentin.foodapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.BaseFragment
import com.falin.valentin.foodapp.utils.Logger

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

    private val logger = Logger(lifecycle, Logger.Owner.FAVORITE_TAB_FRAGMENT)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_tab, container, false)
    }
}