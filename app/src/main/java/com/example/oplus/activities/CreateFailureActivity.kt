package com.example.oplus.activities

import android.view.View
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class CreateFailureActivity: BaseActivity() {

    override fun initView() {
        super.initView()
        initToolbar(tvTitleMenu, "CHI TIẾT SỰ CỐ")
        imgSearch.visibility = View.GONE
    }
    override fun getResource(): Int {
        return R.layout.activity_create_failure
    }

    override fun getBackImage(): View? {
        return imgBack
    }
}