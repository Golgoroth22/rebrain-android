package com.falin.valentin.foodapp.screen.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.falin.valentin.foodapp.R
import kotlinx.android.synthetic.main.layout_custom_button.view.*

/**
 * [LinearLayout] subclass to work with custom view [CustomButtonView] and showing it.
 *
 */
class CustomButtonView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_custom_button, this)

        custom_button_linear.orientation = LinearLayout.VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonView)
        custom_button_image.setImageDrawable(attributes.getDrawable(R.styleable.CustomButtonView_image))
        custom_button_text.text = attributes.getString(R.styleable.CustomButtonView_text)
        attributes.recycle()
    }
}