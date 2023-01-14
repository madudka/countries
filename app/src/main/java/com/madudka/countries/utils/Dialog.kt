package com.madudka.countries.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madudka.countries.R

fun showDialog(context: Context, error: String){
    val msg = context.getString(R.string.error_message, error)
    MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.error_title))
        .setMessage(msg)
        .setIcon(R.drawable.ic_baseline_error_outline)
        .setPositiveButton(context.getString(R.string.close)) { _,_ -> }
        .show()
}