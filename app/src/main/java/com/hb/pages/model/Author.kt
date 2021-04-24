package com.hb.pages.model

import android.os.Parcelable
import com.hb.pages.util.NO_ID
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val id: Long = NO_ID,
    val name: String
) : Parcelable