package com.example.oplus.activities

import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.adapter.PropertiesDeviceAdapter
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.inventory.FarmDevice
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_detail_device.*

import kotlinx.android.synthetic.main.view_detail_item.*

class DetailDeviceActivity : BaseActivity() {
    private var viewPagerAdapter: ViewpageImageDeviceAdater? = null
    private var inventoryViewModel: InventoryViewModel? = null
    private var device: FarmDevice? = null
    private var propertiesDeviceAdapter: PropertiesDeviceAdapter? = null
    var type= ""
    override fun getBackImage(): View? {
        return ivExit
    }
    override fun initView() {
        super.initView()
        device = intent?.getParcelableExtra("DATA")
        type = intent?.getStringExtra("TYPE").toString()
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.getThuocTinhDong(device?.listName ?: "", device?.itemId ?: 0)
        viewModelObserve()
        recyclerviewDetail()
        if (type == ScreenIDEnum.QR_SCAN_FROM_NAVIGATION.value){
            ctMenuBottom.visibility = View.GONE
        }
    }
    override fun getResource(): Int {
        return R.layout.activity_detail_device
    }



    private fun viewModelObserve() {
        inventoryViewModel?.thuoctinh?.observe(this, {
            val listProperties = it?.thuocTinh
            listProperties.let {
                propertiesDeviceAdapter?.setData(listProperties ?: mutableListOf())
            }
            tvNameDevice.text = it?.title
            viewPagerAdapter = ViewpageImageDeviceAdater(supportFragmentManager, it?.hinh)
            vpImgDevice.adapter = viewPagerAdapter
            pivImg.count = 2
            pivImg.selection = 3
            hideLoading()
        })

    }

    private fun recyclerviewDetail() {
        propertiesDeviceAdapter = PropertiesDeviceAdapter(mutableListOf())
        rvProperties.layoutManager = GridLayoutManager(this, 1)
        rvProperties.setHasFixedSize(true)

        rvProperties.adapter = propertiesDeviceAdapter
        vpImgDevice.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                pivImg.selection = position
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })


    }
}