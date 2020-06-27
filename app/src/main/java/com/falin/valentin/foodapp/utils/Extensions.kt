package com.falin.valentin.foodapp.utils

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.falin.valentin.foodapp.R

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
        RationaleDialog.newInstance(requestId, finishActivity)
            .show(activity.supportFragmentManager, "dialog")
    } else {
        // Location permission has not been granted yet, request it.
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestId)
    }
}

/**
 * A dialog that explains the use of the location permission and requests the necessary
 * permission.
 *
 *
 * The activity should implement
 * [androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback]
 * to handle permit or denial of this permission request.
 */
class RationaleDialog : DialogFragment() {
    private var finishActivity = false
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val requestCode =
            arguments?.getInt(ARGUMENT_PERMISSION_REQUEST_CODE) ?: 0
        finishActivity =
            arguments?.getBoolean(ARGUMENT_FINISH_ACTIVITY) ?: false
        return AlertDialog.Builder(activity)
            .setMessage("R.string.permission_rationale_location")
            .setPositiveButton(android.R.string.ok) { dialog, which -> // After click on Ok, request the permission.
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    requestCode
                )
                // Do not finish the Activity while requesting permission.
                finishActivity = false
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (finishActivity) {
            Toast.makeText(activity, "permission_required_toast", Toast.LENGTH_SHORT).show()
            activity?.finish()
        }
    }

    companion object {
        private const val ARGUMENT_PERMISSION_REQUEST_CODE = "requestCode"
        private const val ARGUMENT_FINISH_ACTIVITY = "finish"

        /**
         * Creates a new instance of a dialog displaying the rationale for the use of the location
         * permission.
         *
         *
         * The permission is requested after clicking 'ok'.
         *
         * @param requestCode    Id of the request that is used to request the permission. It is
         * returned to the
         * [androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback].
         * @param finishActivity Whether the calling Activity should be finished if the dialog is
         * cancelled.
         */
        fun newInstance(requestCode: Int = 0, finishActivity: Boolean): RationaleDialog {
            val arguments = Bundle().apply {
                putInt(ARGUMENT_PERMISSION_REQUEST_CODE, requestCode)
                putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity)
            }
            return RationaleDialog().apply {
                this.arguments = arguments
            }
        }
    }
}