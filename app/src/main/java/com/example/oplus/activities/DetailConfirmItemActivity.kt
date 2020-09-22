package com.example.oplus.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oplus.R
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.ItemConfirmInventory
import kotlinx.android.synthetic.main.activity_detail_confirm_item.*
import kotlinx.android.synthetic.main.view_detail_item.*

class DetailConfirmItemActivity : AppCompatActivity() {
    private var item: ItemConfirmInventory? = null
    private var viewpageAdapter: ViewpageImageDeviceAdater? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_confirm_item)
        onClick()
        item = intent?.getParcelableExtra("DATA1")
        item?.listThongtin = mutableListOf()
        item?.soLuongTon?.let { item?.listThongtin?.add(it) }
        item?.donViTinh?.let { item?.listThongtin?.add(it) }
        item?.ma?.let { item?.listThongtin?.add(it) }
        item?.loai?.let { item?.listThongtin?.add(it) }

        item?.listHinh = mutableListOf()
        item?.thumbnail?.let{
            item?.listHinh?.add(it)
        }
        tvNameDevice.text = item?.title
        viewpageAdapter = ViewpageImageDeviceAdater(supportFragmentManager, item?.listHinh)
        vpImgDevice.adapter = viewpageAdapter
        pivImg.count = 2
        pivImg.selection = 3
    }

    private fun onClick() {
        ivExit.setOnClickListener {
            onBackPressed()
        }
        imgDong.setOnClickListener {
            onBackPressed()
        }
    }
}