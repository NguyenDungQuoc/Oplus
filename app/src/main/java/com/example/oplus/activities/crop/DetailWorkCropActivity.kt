package com.example.oplus.activities.crop

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.ZoomImageActivity
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.adapter.ImgAttachAdapter
import com.example.oplus.model.crop.ResultDetailWorkCropDTO
import com.example.oplus.viewmodel.CropsViewModel
import kotlinx.android.synthetic.main.activity_detail_work_crop.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import java.util.*

class DetailWorkCropActivity : BaseActivity() {
    var cropsViewModel: CropsViewModel? = null
    var mAdapter: ImgAttachAdapter? = null
    var id = 0
    override fun initView() {
        super.initView()
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        id = intent.getIntExtra("ID",1)
        cropsViewModel?.getChiTietLichLamViec(id)
        defaultView()
        recyclerView()
        onClickEvent()
        observer()
    }

    private fun observer() {
        cropsViewModel?.detailWork?.observe(this,{
            bindingData(it)
        })
    }

    private fun defaultView() {
        imgSearch.visibility = View.GONE
        initToolbar(tvTitleMenu,"PHÁT HIỆN SÂU")
    }

    override fun getResource(): Int {
        return R.layout.activity_detail_work_crop
    }

    override fun getBackImage(): View? {
        return imgBack
    }
    private fun recyclerView() {
        mAdapter = ImgAttachAdapter(mutableListOf())
        rvImgCrop.layoutManager = GridLayoutManager(this, 3)
        rvImgCrop.setHasFixedSize(true)
        rvImgCrop.adapter = mAdapter
    }

    private fun onClickEvent() {
        tvDongCrop.setOnClickListener {
            onBackPressed()
        }

        mAdapter?.onClick = {
            val intent = Intent(this, ZoomImageActivity::class.java)
            intent.putExtra("IMAGE",it)
            startActivity(intent)
        }
        lnBug.setOnClickListener {
            val intent = Intent(this, BugsActivity::class.java)
//            intent.putExtra("IMAGE",it)
            startActivity(intent)
        }
    }

    fun bindingData(item:ResultDetailWorkCropDTO?){
        item?.apply {
            tvBugName.text = title?.title
            tvBugValue.text = title?.value
            tvNumber.text = soRo.toString()
            tvDinhKemCrop.text = labelHinh
            tvDongCrop.text = buttons?.get(0)?.title?.toUpperCase(Locale.ROOT)
            val colorIn = "#ffffff"
            val color: Int = Color.parseColor(buttons?.get(0)?.maMau ?: colorIn)
            (tvDongCrop.background as GradientDrawable).setColor(color)
            item.media?.let { mAdapter?.insertData(it) }
        }
    }
}