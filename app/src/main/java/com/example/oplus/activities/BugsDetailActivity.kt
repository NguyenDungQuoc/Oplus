package com.example.oplus.activities


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.adapter.BugsPropertiesAdapter
import com.example.oplus.adapter.ImgAttachAdapter
import com.example.oplus.model.crop.PropertiesBugDTO
import com.example.oplus.viewmodel.CropsViewModel

import kotlinx.android.synthetic.main.activity_bugs_detail.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import java.util.*

class BugsDetailActivity : BaseActivity() {
    var imgAdapter: ImgAttachAdapter? = null
    var cropsViewModel: CropsViewModel? = null
    var id = 0
    var mAdapter:BugsPropertiesAdapter? =null
    override fun initView() {
        super.initView()
        defaultView()
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        id = intent.getIntExtra("ID_ITEM", 1)
        cropsViewModel?.layChiTietSauHai(id)
        showLoading()
        recyclerView()
        observer()
        onClickEvent()
    }

    private fun onClickEvent() {
        imgAdapter?.onClick = {
            val intent = Intent(this, ZoomImageActivity::class.java)
            intent.putExtra("IMAGE",it)
            startActivity(intent)
        }
        tvDongBug.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observer() {
       cropsViewModel?.propertiesBug?.observe(this,{
           it?.thuocTinh?.let { it1 -> mAdapter?.bindingData(it1) }
           bindingData(it ?: PropertiesBugDTO())
           hideLoading()

       })
    }

    private fun recyclerView() {
        //properties
        mAdapter = BugsPropertiesAdapter(mutableListOf())
        rvPropertiesBug.layoutManager = LinearLayoutManager(this)
        rvPropertiesBug.setHasFixedSize(true)
        rvPropertiesBug.adapter = mAdapter
        //image
        imgAdapter = ImgAttachAdapter(mutableListOf())
        rvImgBug.layoutManager = GridLayoutManager(this, 3)
        rvImgBug.setHasFixedSize(true)
        rvImgBug.adapter = imgAdapter
    }

    private fun defaultView() {
        initToolbar(tvTitleMenu,"CHI TIẾT SÂU HẠI")
        imgSearch.visibility = View.GONE
    }

    override fun getResource(): Int {
        return R.layout.activity_bugs_detail
    }

    override fun getBackImage(): View? {
        return imgBack
    }
    fun bindingData(item: PropertiesBugDTO) {
        item.apply {
            tvDinhKemBug.text = hinh
            tvDongBug.text = buttons?.get(0)?.title?.toUpperCase(Locale.ROOT)
            val colorIn = "#ffffff"
            val color: Int = Color.parseColor(buttons?.get(0)?.maMau ?: colorIn)
            (tvDongBug.background as GradientDrawable).setColor(color)
            item.media?.let { imgAdapter?.insertData(it) }

        }
    }
}