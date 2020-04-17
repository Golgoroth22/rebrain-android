package com.falin.valentin.foodapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * This [FragmentActivity] extension method can be called for create [ViewModel].
 *
 * @param factory factory for creating [ViewModel]
 *
 * @return new [ViewModel] object
 */
inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

/**
 * This [Fragment] extension method can be called for create [ViewModel].
 *
 * @param factory factory for creating [ViewModel]
 *
 * @return new [ViewModel] object
 */
inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

/**
 * This [EditText] extension method can be called for add TextChangedListener to EditText.
 *
 * @param onTextChanged Lyambda with [String] param
 */
fun EditText.setOnTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            removeTextChangedListener(this)
            onTextChanged.invoke(s?.toString() ?: "")
            addTextChangedListener(this)
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}