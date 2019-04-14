package com.falin.valentin.foodapp.screen.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.falin.valentin.foodapp.R

/**
 * [LinearLayout] subclass to work with custom view [CustomBottomBarView] and showing it.
 *
 */
class CustomBottomBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.layout_custom_bottom_bar, this)
    }
}