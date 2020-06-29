package com.falin.valentin.foodapp.screen.dialog

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment

/**
 * A dialog that explains the use of the location permission and requests the necessary
 * permission.
 *
 *
 * The activity should implement
 * [androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback]
 * to handle permit or denial of this permission request.
 */
class RationaleDialogFragment : DialogFragment() {
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
        fun newInstance(requestCode: Int = 0, finishActivity: Boolean): RationaleDialogFragment {
            val arguments = Bundle().apply {
                putInt(ARGUMENT_PERMISSION_REQUEST_CODE, requestCode)
                putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity)
            }
            return RationaleDialogFragment().apply {
                this.arguments = arguments
            }
        }
    }
}