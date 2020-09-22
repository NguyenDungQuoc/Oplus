package com.example.oplus.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.oplus.R
import com.example.oplus.adapter.PropertiesDeviceAdapter
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.FarmDevice
import com.example.oplus.model.ItemConfirmInventory
import com.example.oplus.viewmodel.InventoryViewModel

import kotlinx.android.synthetic.main.view_detail_item.*

class DetailDeviceActivity : AppCompatActivity() {
    private var viewpageAdapter: ViewpageImageDeviceAdater? = null
    private var inventoryViewModel: InventoryViewModel? = null
    private var device: FarmDevice? = null

    private var propertiesDeviceAdapter: PropertiesDeviceAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_device)


        device = intent?.getParcelableExtra("DATA")
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.getThuocTinhDong(device?.listName ?: "", device?.itemId ?: 0)

        viewModelObserve()
        recycleviewDetail()
        onClick()
    }

    private fun onClick() {
        ivExit.setOnClickListener {
            onBackPressed()
        }
    }

    private fun viewModelObserve() {
        inventoryViewModel?.thuoctinh?.observe(this, {
            val listProperties = it?.result?.thuocTinh
            listProperties.let {
                propertiesDeviceAdapter?.setData(listProperties ?: mutableListOf())
            }
            tvNameDevice.text = it?.result?.title
            viewpageAdapter = ViewpageImageDeviceAdater(supportFragmentManager, it?.result?.hinh)
            vpImgDevice.adapter = viewpageAdapter
            pivImg.count = 2
            pivImg.selection = 3
        })

    }

    private fun recycleviewDetail() {
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