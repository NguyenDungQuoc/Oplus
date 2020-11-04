package com.example.oplus.activities

import android.view.View
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_checklist.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class CheckListActivity: BaseActivity() {
    override fun initView() {
        imgBack.setImageResource(R.drawable.icon_exit)
        super.initView()
        initToolbar(tvTitleMenu, "kiểm tra hạng mục")
        imgSearch.visibility = View.GONE
        materialButton.setBackgroundColor(resources.getColor(R.color.red))
    }
    override fun getResource(): Int {
        return R.layout.activity_checklist
    }

    override fun getBackImage(): View? {
        return imgBack
    }
}