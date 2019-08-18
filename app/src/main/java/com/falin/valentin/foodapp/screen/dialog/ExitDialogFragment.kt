package com.falin.valentin.foodapp.screen.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.falin.valentin.foodapp.R
import kotlinx.android.synthetic.main.dialog_layout.view.*

class ExitDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val rootView = inflater.inflate(R.layout.dialog_layout, null)
        rootView.dialog_positiveButton.setOnClickListener {
            activity!!.finish()
        }
        rootView.dialog_negativeButton.setOnClickListener {
            this.dismiss()
        }
        builder.setView(rootView)
        return builder.create()
    }
}