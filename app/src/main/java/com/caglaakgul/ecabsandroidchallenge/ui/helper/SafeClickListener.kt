package com.caglaakgul.ecabsandroidchallenge.ui.helper

import android.os.SystemClock
import android.view.View

class SafeClickListener (
    private val interval: Int = 600,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastClickTime: Long = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < interval) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}