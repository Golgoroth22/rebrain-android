package com.falin.valentin.foodapp.viewmodel

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import com.falin.valentin.foodapp.R
import com.falin.valentin.foodapp.screen.main.FavoriteTabFragment
import com.falin.valentin.foodapp.screen.main.MainTabFragment
import com.falin.valentin.foodapp.screen.main.adapter.MainTabElementAdapter

/**
 * [ViewModel] subclass for work with [MainActivity].
 *
 * @property mainTabFragment fragment for main tab
 * @property favoriteTabFragment fragment for favorite tab
 */
class MainActivityViewModel : ViewModel() {
    var mainTabFragment: MainTabFragment = MainTabFragment.newInstance()
    var favoriteTabFragment: FavoriteTabFragment = FavoriteTabFragment.newInstance()

    /**
     * This method can be called for switch fragment adapter display mode.
     *
     */
    fun updateMenuItem(item: MenuItem?) {
        mainTabFragment.switchRecyclerViewDisplayMode()
        when (mainTabFragment.getLayoutManagerDisplayMode()) {
            MainTabElementAdapter.LayoutManagerDisplayMode.GRID.ordinal -> item?.setIcon(R.drawable.ic_menu_linear)
            MainTabElementAdapter.LayoutManagerDisplayMode.LINEAR.ordinal -> item?.setIcon(R.drawable.ic_menu_grid)
        }
    }
}