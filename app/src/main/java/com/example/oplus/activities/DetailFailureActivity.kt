package com.example.oplus.activities

import com.example.oplus.activities.base.BaseDetailFailureActivity
import com.example.oplus.model.ResultBaseDetail


class DetailFailureActivity : BaseDetailFailureActivity() {
    var id = 0
    override fun initView() {
        super.initView()
        id = intent.getIntExtra("ID", 1)
        failureViewModel?.chiTietCongViec(id)
        observer()
    }
    private fun observer() {
        failureViewModel?.item?.observe(this, {
            bindingData(it ?: ResultBaseDetail())
            hideLoading()
        })
    }
}