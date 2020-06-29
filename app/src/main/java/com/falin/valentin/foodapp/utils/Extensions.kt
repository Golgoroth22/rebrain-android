package com.falin.valentin.foodapp.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.screen.dialog.RationaleDialogFragment

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

/**
 * This [Context] extension method can be called for simple start new activity.
 *
 * @param T activity class
 */
inline fun <reified T> Context.launchActivity() {
    startActivity(Intent(this, T::class.java))
}

/**
 * Checks if the result contains a [PackageManager.PERMISSION_GRANTED] result for a
 * permission from a runtime permissions request.
 *
 * @see androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
 */
fun isPermissionGranted(
    grantPermissions: Array<String>, grantResults: IntArray, permission: String
): Boolean {
    for (i in grantPermissions.indices) {
        if (permission == grantPermissions[i]) {
            return grantResults[i] == PackageManager.PERMISSION_GRANTED
        }
    }
    return false
}

/**
 * Requests the fine location permission. If a rationale with an additional explanation should
 * be shown to the user, displays a dialog that triggers the request.
 */
fun requestPermission(
    activity: AppCompatActivity, requestId: Int,
    permission: String, finishActivity: Boolean
) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
        // Display a dialog with rationale.
        RationaleDialogFragment.newInstance(requestId, finishActivity)
            .show(activity.supportFragmentManager, "dialog")
    } else {
        // Location permission has not been granted yet, request it.
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestId)
    }
}