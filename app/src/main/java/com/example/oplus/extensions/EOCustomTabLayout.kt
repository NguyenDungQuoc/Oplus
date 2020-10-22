package com.example.oplus.extensions

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.khieu.nguyen.expandablecalendar.data.pxFromDp

open class EOCustomTabLayout(context: Context, attrs: AttributeSet?) : TabLayout(context, attrs) {

    init {
        this.setTabRippleColorResource(android.R.color.transparent)
//        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//
//                viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//        })
        addOnLayoutChangeListener { view, i, i2, i3, i4, i5, i6, i7, i8 ->
            if (tabCount == 0) return@addOnLayoutChangeListener
            var totalWidth = 0
            var screenWidth = resources.displayMetrics.widthPixels
            val paddingLeftAndRight = 12f.pxFromDp(context).toInt()
            val maxWidth = screenWidth / tabCount
            val tabNeedChange: MutableList<Int> = mutableListOf()
            for (i in 0 until tabCount) {
                val tabWidth = (getTabAt(i)?.customView?.width ?: 0) + paddingLeftAndRight
                if (tabWidth >= maxWidth) {
                    screenWidth -= tabWidth
                } else {
                    tabNeedChange.add(i)
                }
                totalWidth += tabWidth
            }

            if (tabCount > 0) {
                if (totalWidth < resources.displayMetrics.widthPixels && tabNeedChange.size > 0) {
                    val remainSize = screenWidth / tabNeedChange.size - paddingLeftAndRight
                    for (i in tabNeedChange) {
                        val tabWidth =
                            (getTabAt(i)?.customView?.width ?: 0) + paddingLeftAndRight
                        if (tabWidth < remainSize) {
                            getTabAt(i)?.customView?.layoutParams?.width = remainSize
                            getTabAt(i)?.customView?.requestLayout()
                        }
                    }
                }
            }
        }
    }

    fun addLayoutChange() {
    }

    fun withViewPager(viewPg: ViewPager) {
        addOnTabSelectedListener(object : EOTabSelectListener {
            override fun onTabSelected(p0: Tab?) {
                viewPg.currentItem = p0?.position ?: 0
            }
        })
    }

    fun withViewPager2(viewPg: ViewPager2) {
        addOnTabSelectedListener(object : EOTabSelectListener {
            override fun onTabSelected(p0: Tab?) {
                viewPg.setCurrentItem(p0?.position ?: 0, false)
            }
        })
    }

    interface EOTabSelectListener : OnTabSelectedListener {
        override fun onTabReselected(p0: Tab?) {

        }

        override fun onTabUnselected(p0: Tab?) {

        }

        override fun onTabSelected(p0: Tab?) {

        }
    }
}