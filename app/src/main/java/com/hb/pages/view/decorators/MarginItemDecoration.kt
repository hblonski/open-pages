package com.hb.pages.view.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val topMargin: Int = 0,
    private val leftMargin: Int = 0,
    private val rightMargin: Int = 0,
    private val bottomMargin: Int = 0,
    private val applyToFirstItem: Boolean = true
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top =
                if (!applyToFirstItem && parent.getChildAdapterPosition(view) == 0) 0 else topMargin
            left = leftMargin
            right = rightMargin
            bottom = bottomMargin
        }
    }
}