package com.yoite.chibissapp.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yoite.chibissapp.R


fun decorationList(context: Context): RecyclerView.ItemDecoration = object : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        outRect.set(0,
            0,
            0,
            if (position == parent.adapter!!.itemCount - 1) 0 else context.resources.getDimensionPixelSize(
                R.dimen.divider))
    }
}