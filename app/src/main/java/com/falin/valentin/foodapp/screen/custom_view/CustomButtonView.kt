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
class CustomButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var isButtonChecked: Boolean
    var checkedImageId: Int
    var uncheckedImageId: Int
    lateinit var tabType: MainTabType

    init {
        inflate(context, R.layout.layout_custom_button, this)

        custom_button_linear.orientation = VERTICAL

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonView)
        uncheckedImageId = attributes.getResourceId(R.styleable.CustomButtonView_on_image, 0)
        custom_button_image.setImageResource(uncheckedImageId)
        checkedImageId = attributes.getResourceId(R.styleable.CustomButtonView_off_image, 0)
        custom_button_text.text = attributes.getString(R.styleable.CustomButtonView_text)
        isButtonChecked = attributes.getBoolean(R.styleable.CustomButtonView_isChecked, false)
        setChecked(isButtonChecked)
        attributes.recycle()
    }

    fun setChecked(isChecked: Boolean) {
        isButtonChecked = isChecked
        if (isButtonChecked) {
            custom_button_image.setImageResource(checkedImageId)
            custom_button_text.setTextColor(resources.getColor(R.color.colorCustomCheckButtonText))
        } else {
            custom_button_image.setImageResource(uncheckedImageId)
            custom_button_text.setTextColor(resources.getColor(R.color.colorCustomUncheckedButtonText))
        }
    }
}