package com.example.oplus.fragment.failure

import android.content.Intent
import com.example.oplus.activities.CreateFailureActivity
import com.example.oplus.activities.DetailFailureQRScanActivity
import com.example.oplus.fragment.base.BaseBarCodeFragment
import com.example.oplus.model.QRCodeFailureDTO
import com.example.oplus.viewmodel.BaseViewModel
import com.google.gson.Gson
import com.google.zxing.Result

class QRScanFailureFragment : BaseBarCodeFragment() {

    override fun initView() {
        super.initView()
        observer()
    }

    private fun observer() {
        failureViewModel?.item?.observe(this, {
            if (it == null){
                val intent = Intent(this.context, CreateFailureActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this.context, DetailFailureQRScanActivity::class.java)
                intent.putExtra("ITEM", it).putExtra("TYPE", type)
                startActivity(intent)
            }
        })
    }

    override fun getViewModel(): BaseViewModel {
        return failureViewModel!!
    }

    override fun handleResult(rawResult: Result?) {
        val item = Gson().fromJson(rawResult?.text, QRCodeFailureDTO::class.java)
        item.ItemID?.let { failureViewModel?.laySuCoCuaThietBi(it) }

    }
}