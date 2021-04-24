package com.hb.pages.model

import android.os.Parcelable
import com.hb.pages.util.NO_ID
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class Book (
    val id: Long = NO_ID,
    val title: String,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val isbn: String? = null,
    val pageCount: Int,
    val imageURL: String? = null,
    val language: String? = null,
    val purchased: Boolean = true,
    val pagesRead: Int = 0,
    val comments: String? = null,
    val authors: List<Author> = emptyList(),
    val rating: Float = 0F,
    val startedReadingOn: LocalDate? = null,
    val finishedReadingOn: LocalDate? = null,
    val format: BookFormat = BookFormat.DIGITAL
) : Parcelable