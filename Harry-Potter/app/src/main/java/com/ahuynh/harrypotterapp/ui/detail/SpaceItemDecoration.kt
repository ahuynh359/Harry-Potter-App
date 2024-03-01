package com.ahuynh.harrypotterapp.ui.detail

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(private val space: Int, private val numCol: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            right = space
            bottom = space
            left = space
        }

        val position = parent.getChildAdapterPosition(view)
        if (numCol <= 2) {
            if (position % numCol == 0) {
                outRect.left = space
                outRect.right = space / 2
            } else if (position % numCol != 0) {

                outRect.left = space / 2
                outRect.right = space

            }
        } else {
            when {
                position % numCol == 0 -> {
                    outRect.left = space
                    outRect.right = space / 2
                }

                position % numCol == numCol - 1 -> {
                    outRect.left = space / 2
                    outRect.right = space
                }
                else -> {
                    outRect.left = space / 2
                    outRect.right = space / 2
                }
            }

        }
    }
}