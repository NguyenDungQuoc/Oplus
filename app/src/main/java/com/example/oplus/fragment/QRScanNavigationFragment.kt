package com.example.oplus.fragment

import android.content.Intent
import com.example.oplus.activities.DetailDeviceActivity
import com.example.oplus.fragment.base.BaseBarCodeFragment
import com.example.oplus.model.QRCodeFailureDTO
import com.example.oplus.model.inventory.FarmDevice
import com.example.oplus.viewmodel.BaseViewModel
import com.example.oplus.viewmodel.FailureViewModel
import com.google.gson.Gson
import com.google.zxing.Result

class QRScanNavigationFragment : BaseBarCodeFragment() {
    private var device: FarmDevice? = null
    override fun initView() {
        super.initView()
    }

    override fun getViewModel(): BaseViewModel {
        return failureViewModel!!
    }

    override fun handleResult(rawResult: Result?) {
        val item = Gson().fromJson(rawResult?.text, QRCodeFailureDTO::class.java)
        device = FarmDevice().apply {
            listName = item.ListName
            itemId = item.ItemID

            val intent = Intent(activity, DetailDeviceActivity::class.java)
            intent.putExtra("DATA", device).putExtra("TYPE", type)
            startActivity(intent)
        }
    }
}