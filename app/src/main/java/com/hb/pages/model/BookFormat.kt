package com.hb.pages.model

import androidx.annotation.StringRes
import com.hb.pages.R

enum class BookFormat(val code: Int, val stringCode: String, @StringRes val stringRes: Int) {
    NOT_INFORMED(0, "NOT_INFORMED", R.string.label_not_informed),
    DIGITAL(1, "DIGITAL", R.string.label_book_format_digital),
    PAPERBACK(2, "PAPERBACK", R.string.label_book_format_paperback),
    HARDBACK(3, "HARDBACK", R.string.label_book_format_hardback);

    companion object {
        fun getByCode(code: Int): BookFormat? {
            return values().firstOrNull { it.code == code }
        }

        fun getByStringCode(stringCode: String): BookFormat? {
            return values().firstOrNull { it.stringCode == stringCode }
        }
    }
}