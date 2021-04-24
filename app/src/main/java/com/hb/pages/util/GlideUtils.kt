package com.hb.pages.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun loadImageInto(
    url: String?,
    context: Context,
    imageView: ImageView,
    @DrawableRes placeholder: Int
) {
    Glide
        .with(context)
        .load(url)
        .placeholder(placeholder)
        .dontAnimate()
        .centerCrop()
        .into(imageView)
}

fun loadLocalImageInto(
    image: Bitmap,
    context: Context,
    imageView: ImageView
) {
    Glide
        .with(context)
        .load(image)
        .dontAnimate()
        .centerCrop()
        .into(imageView)
}