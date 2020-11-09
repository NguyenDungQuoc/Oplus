package com.example.oplus.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_work_deail_backlog.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class WorkDetailBacklogActivity : BaseActivity() {
    var isHide: Boolean = false
    override fun initView() {
        super.initView()
        defaultView()
        onClickEvent()
    }

    private fun onClickEvent() {
        click.setOnClickListener {
            isHide = !isHide
            if (isHide) {
                imgHide.visibility = View.GONE
//                imgHide.animate()
//                    .translationY(0f)
//                    .alpha(10f)
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator?) {
//                            super.onAnimationEnd(animation)
//                            imgHide.visibility = View.GONE
//                        }
//                    })
            } else {
                imgHide.visibility = View.VISIBLE
//                imgHide.animate()
//                    .translationY(imgHide.height.toFloat())
//                    .alpha(0f)
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator?) {
//                            super.onAnimationEnd(animation)
//                            imgHide.visibility = View.VISIBLE
//                            imgHide.setBackgroundColor(resources.getColor(R.color.colorPrimary))
//                        }
//                    })
            }

        }
    }

    private fun defaultView() {
        initToolbar(tvTitleMenu, "CHI TIẾT CÔNG VIỆC")
    }

    override fun getResource(): Int {
        return R.layout.activity_work_deail_backlog
    }

    override fun getBackImage(): View? {
        return imgBack
    }

}