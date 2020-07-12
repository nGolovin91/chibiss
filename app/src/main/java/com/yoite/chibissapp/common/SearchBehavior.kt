package com.yoite.chibissapp.common

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView


class SearchBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    var lastScrollPosition = 0

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean = dependency is RecyclerView

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val recycler = dependency as RecyclerView

        val positionY = recycler.scrollY

        if (positionY <= lastScrollPosition) {
            ObjectAnimator.ofFloat(child, "translationY", 0.0f).apply {
                duration = 200
                start()
            }
        } else {
            val height = child.height * 2
            ObjectAnimator.ofFloat(child, "translationY", -height.toFloat()).apply {
                duration = 200
                start()
            }
        }
        lastScrollPosition = positionY

        return true
    }
}