package com.caglaakgul.ecabsandroidchallenge.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.caglaakgul.ecabsandroidchallenge.R

object DialogHelper {

    fun setProgressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}