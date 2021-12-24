package com.phonecheck.pccustomdialog.utils

import android.view.View


internal fun View.OnClickListener.setMultipleClickListener(vararg view: View) {
    for (v in view) {
        v.setOnClickListener(this)
    }
}

internal fun View.isVisible(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}

internal fun View.visible() {
    visibility = View.VISIBLE
}

internal fun View.gone() {
    visibility = View.GONE
}