package com.example.oplus.activities.base

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.ZoomImageActivity
import com.example.oplus.adapter.ImgAttachAdapter
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.activity_base_detail.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import java.util.*

abstract class BaseDetailFailureActivity : BaseActivity() {
    var mAdapter: ImgAttachAdapter? = null
    var type = ""
    var failureViewModel: FailureViewModel? = null
    var result:ResultBaseDetail? = null
    override fun getResource(): Int {
        return R.layout.activity_base_detail
    }

    override fun getBackImage(): View? {
        return imgBack
    }

    override fun initView() {
        super.initView()
        imgSearch.visibility = View.GONE
        initToolbar(tvTitleMenu, "CHI TIẾT SỰ CỐ")

        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)

        recyclerView()
        onClickEvent()
    }

    private fun onClickEvent() {
        tvDong.setOnClickListener {
            onBackPressed()
        }

        mAdapter?.onClick = {
           val intent = Intent(this, ZoomImageActivity::class.java)
            intent.putExtra("IMAGE",it)
            startActivity(intent)
        }
    }

    private fun recyclerView() {
        mAdapter = ImgAttachAdapter(mutableListOf())
        rvImgAttach.layoutManager = GridLayoutManager(this, 4)
        rvImgAttach.setHasFixedSize(true)
        rvImgAttach.adapter = mAdapter
    }



    fun bindingData(item: ResultBaseDetail) {
        item.apply {
            tvTieuDeTitle.text = tieuDe?.title
            tvTieuDeValue.text = tieuDe?.value
            tvThietBiTitle.text = thietBiGiamSat?.title
            tvThietBiValue.text = thietBiGiamSat?.value ?: ""
            tvTinhTrangTitle.text = tinhTrang?.title
            tvTinhTrangValue.text = tinhTrang?.value
            tvThoiGianBaoTitle.text = thoiGianBao?.title
            tvThoiGianBaoValue.text = thoiGianBao?.value
            tvBatDauTitle.text = batDau?.title
            tvBatDauValue.text = batDau?.value
            tvKetThucTitle.text = ketThuc?.title
            tvKetThucValue.text = ketThuc?.value
            tvNguoiXuLyTitle.text = nguoiXuLy?.title
            tvNguoiXuLyValue.text = nguoiXuLy?.value
            tvDinhKemTitle.text = labelHinh
            tvDong.text = buttons?.get(0)?.title?.toUpperCase(Locale.ROOT)
//                button?.get(0)?.MaMau?.toInt()?.let { it1 -> imgDong.setBackgroundColor(it1) }
            val colorIn = "#ffffff"
            val color: Int = Color.parseColor(buttons?.get(0)?.maMau ?: colorIn)
            (tvDong.background as GradientDrawable).setColor(color)
            item.media?.let { mAdapter?.insertData(it) }

        }
    }


}