package com.hb.pages.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.hb.pages.R
import com.hb.pages.extensions.setPresent
import kotlinx.android.synthetic.main.view_custom_dialog.bottomButton
import kotlinx.android.synthetic.main.view_custom_dialog.subtitle
import kotlinx.android.synthetic.main.view_custom_dialog.title
import kotlinx.android.synthetic.main.view_custom_dialog.topButton

open class CustomPopupDialog(context: Context) : Dialog(context) {

    var dialogTitle: String = ""
        set(value) {
            title.text = value
            field = value
        }

    var dialogSubtitle: String = ""
        set(value) {
            subtitle.text = value
            field = value
        }

    var topButtonText: String = ""
        set(value) {
            topButton.text = value
            field = value
        }

    var bottomButtonText: String = ""
        set(value) {
            bottomButton.text = value
            field = value
        }

    var topButtonAction: () -> Unit = {}
        set(value) {
            topButton.setOnClickListener { value() }
            field = value
        }

    var bottomButtonAction: () -> Unit = {}
        set(value) {
            bottomButton.setOnClickListener { value() }
            field = value
        }

    var onBackPressed: () -> Unit = {}
        set(value) {
            setOnCancelListener {
                value()
            }
            field = value
        }

    var showBottomButton: Boolean = true
        set(value) {
            bottomButton.setPresent(value)
            field = value
        }

    init {
        setCanceledOnTouchOutside(true)
        setContentView(R.layout.view_custom_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}