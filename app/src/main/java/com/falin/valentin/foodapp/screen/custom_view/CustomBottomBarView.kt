package com.falin.valentin.foodapp.screen.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.falin.valentin.foodapp.R
import kotlinx.android.synthetic.main.layout_custom_bottom_bar.view.*
import kotlinx.android.synthetic.main.layout_custom_button.view.*

/**
 * [LinearLayout] subclass to work with custom view [CustomBottomBarView] and showing it.
 *
 */
class CustomBottomBarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs), View.OnClickListener {

    lateinit var customListener: onClickCustomListener

    init {
        inflate(context, R.layout.layout_custom_bottom_bar, this)
        makeMainButtonGreen()
        custom_bottom_bar_main_button.setOnClickListener(this)
        custom_bottom_bar_favorite_button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        customListener.onClick(view)
        buttonClick(view)
    }

    private fun buttonClick(view: View?) {
        if (view is CustomButtonView) {
            when (view.labelPosition) {
                1 -> {
                    firstButtonClicked()
                }
                2 -> {
                    secondButtonClicked()
                }
            }
        }
    }

    private fun secondButtonClicked() {
        makeFavoriteButtonGreen()
        makeMainButtonGrey()
    }

    private fun firstButtonClicked() {
        makeMainButtonGreen()
        makeFavoriteButtonGrey()
    }

    private fun makeMainButtonGrey() {
        custom_bottom_bar_main_button.custom_button_text.setTextColor(resources.getColor(R.color.colorAppAccent))
        custom_bottom_bar_main_button.custom_button_image.setImageResource(R.drawable.main_off)
    }

    private fun makeMainButtonGreen() {
        custom_bottom_bar_main_button.custom_button_text.setTextColor(resources.getColor(R.color.colorCustomButtonText))
        custom_bottom_bar_main_button.custom_button_image.setImageResource(R.drawable.main_on)
    }

    private fun makeFavoriteButtonGrey() {
        custom_bottom_bar_favorite_button.custom_button_text.setTextColor(resources.getColor(R.color.colorAppAccent))
        custom_bottom_bar_favorite_button.custom_button_image.setImageResource(R.drawable.favorites_off)
    }

    private fun makeFavoriteButtonGreen() {
        custom_bottom_bar_favorite_button.custom_button_text.setTextColor(resources.getColor(R.color.colorCustomButtonText))
        custom_bottom_bar_favorite_button.custom_button_image.setImageResource(R.drawable.favorites_on)
    }

    fun setOnCustomClickListener(listener: onClickCustomListener) {
        customListener = listener
    }
}

interface onClickCustomListener {
    fun onClick(view: View?)
}