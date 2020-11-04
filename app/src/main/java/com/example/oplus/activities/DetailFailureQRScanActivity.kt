package com.example.oplus.activities

import com.example.oplus.activities.base.BaseDetailFailureActivity
import com.example.oplus.model.ResultBaseDetail

class DetailFailureQRScanActivity : BaseDetailFailureActivity() {
    override fun initView() {
        super.initView()
        result = ResultBaseDetail()
        result = intent.getParcelableExtra("ITEM")
        bindingData(result ?: ResultBaseDetail())
    }
}