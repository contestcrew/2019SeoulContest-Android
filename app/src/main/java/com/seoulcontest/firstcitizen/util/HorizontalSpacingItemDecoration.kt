package com.seoulcontest.firstcitizen.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpacingItemDecoration(var spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        var position = parent.getChildViewHolder(view).adapterPosition
        var itemCount = state.itemCount

        setSpacing(outRect, position, itemCount)
    }

    fun setSpacing(outRect: Rect, position: Int, itemCount: Int) {

        outRect.left = spacing
        outRect.right = if (position == itemCount - 1) spacing else 0
        outRect.top = spacing
        outRect.bottom = spacing

    }
}