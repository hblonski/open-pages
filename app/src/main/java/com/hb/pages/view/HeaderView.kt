package com.hb.pages.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import com.hb.pages.R
import com.hb.pages.extensions.setPresent
import kotlinx.android.synthetic.main.view_header.view.backButton
import kotlinx.android.synthetic.main.view_header.view.leftButton
import kotlinx.android.synthetic.main.view_header.view.middleButton
import kotlinx.android.synthetic.main.view_header.view.rightButton
import kotlinx.android.synthetic.main.view_header.view.title

class HeaderView(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    var titleText: String = ""
        set(value) {
            title.text = value
            field = value
        }

    var backButtonAction: () -> Unit = {}
        set(value) {
            backButton.setOnClickListener { value() }
            field = value
        }

    @ColorRes
    var titleColor: Int = R.color.primaryPurple
        set(value) {
            title.setTextColor(ColorStateList.valueOf(value))
            field = value
        }

    var rightButtonIcon: Drawable? = null
        set(value) {
            rightButton.setImageDrawable(value)
            rightButton.setPresent(value != null)
            field = value
        }

    var rightButtonAction: () -> Unit = { /* Empty */ }
        set(value) {
            rightButton.setOnClickListener { value() }
            field = value
        }

    var middleButtonIcon: Drawable? = null
        set(value) {
            middleButton.setImageDrawable(value)
            middleButton.setPresent(value != null)
            field = value
        }

    var middleButtonButtonAction: () -> Unit = { /* Empty */ }
        set(value) {
            middleButton.setOnClickListener { value() }
            field = value
        }

    var leftButtonIcon: Drawable? = null
        set(value) {
            leftButton.setImageDrawable(value)
            leftButton.setPresent(value != null)
            field = value
        }

    var leftButtonAction: () -> Unit = { /* Empty */ }
        set(value) {
            leftButton.setOnClickListener { value() }
            field = value
        }

    init {
        inflate(context, R.layout.view_header, this)

        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.HeaderView)

        titleColor = attributes.getColor(
            R.styleable.HeaderView_titleColor,
            context.getColor(R.color.primaryPurple)
        )

        titleText = attributes.getString(R.styleable.HeaderView_titleText) ?: ""

        rightButtonIcon = attributes.getDrawable(R.styleable.HeaderView_rightButtonDrawable)

        middleButtonIcon =
            attributes.getDrawable(R.styleable.HeaderView_middleButtonDrawable)

        leftButtonIcon = attributes.getDrawable(R.styleable.HeaderView_leftButtonDrawable)

        attributes.recycle()
    }
}