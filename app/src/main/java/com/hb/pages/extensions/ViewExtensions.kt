package com.hb.pages.extensions

import android.view.View

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun View.setPresent(isPresent: Boolean) {
    if (isPresent) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}