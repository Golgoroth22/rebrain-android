package com.falin.valentin.foodapp.screen.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.falin.valentin.foodapp.R
import kotlinx.android.synthetic.main.layout_custom_bottom_bar.view.*

/**
 * [LinearLayout] subclass to work with custom view [CustomBottomBarView] and showing it.
 *
 */
class CustomBottomBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), View.OnClickListener {

    lateinit var customListener: OnClickCustomListener
    private val tabButtonsMap by lazy {
        hashMapOf<MainTabType, CustomButtonView>(
            MainTabType.MAIN to custom_bottom_bar_main_button,
            MainTabType.FAVORITE to custom_bottom_bar_favorite_button,
            MainTabType.ACCOUNT to custom_bottom_bar_account_button
        )
    }

    init {
        inflate(context, R.layout.layout_custom_bottom_bar, this)
        custom_bottom_bar_main_button.tabType = MainTabType.MAIN
        custom_bottom_bar_favorite_button.tabType = MainTabType.FAVORITE
        custom_bottom_bar_account_button.tabType = MainTabType.ACCOUNT
        custom_bottom_bar_main_button.setOnClickListener(this)
        custom_bottom_bar_favorite_button.setOnClickListener(this)
        custom_bottom_bar_account_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val tabType = (view as CustomButtonView).tabType
        customListener.onClick(tabType)
        updateTabsSelection(tabType)
    }

    private fun updateTabsSelection(selectedTabType: MainTabType = MainTabType.MAIN) {
        checkSelectedTab(selectedTabType)
        uncheckOthersTab(selectedTabType)
    }

    private fun uncheckOthersTab(selectedTabType: MainTabType) {
        tabButtonsMap.keys
            .filter { it != selectedTabType }
            .forEach {
                tabButtonsMap[it]?.setChecked(false)
            }
    }

    private fun checkSelectedTab(selectedTabType: MainTabType) {
        tabButtonsMap[selectedTabType]?.setChecked(true)
    }

    fun setOnCustomClickListener(listener: OnClickCustomListener) {
        customListener = listener
    }
}

/**
 * Custom listener to work with in in [MainActivity].
 *
 */
interface OnClickCustomListener {
    fun onClick(tabType: MainTabType)
}

/**
 * Enum class for work with enum values to [CustomButtonView].
 *
 */
enum class MainTabType {
    MAIN, FAVORITE, ACCOUNT
}