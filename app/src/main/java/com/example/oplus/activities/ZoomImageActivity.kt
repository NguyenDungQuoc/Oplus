package com.example.oplus.activities


import android.view.View
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.failure.MediaDTO
import kotlinx.android.synthetic.main.activity_zoom_img.*

class ZoomImageActivity : BaseActivity() {
    var item: MediaDTO? = null


    override fun getResource(): Int {
        return R.layout.activity_zoom_img
    }

    override fun getBackImage(): View? {
        return null
    }

    override fun initView() {
        super.initView()
        loadingDialog?.hide()
        item = MediaDTO()
        item = intent.getParcelableExtra("IMAGE")
        Glide.with(pvImg.context).load(item?.fullUrl).into(pvImg)
        tvUpdateTime.text = item?.uploadDate
        imgClose.setOnClickListener {
            onBackPressed()
        }
        tvXoa.isEnabled = false
    }
}