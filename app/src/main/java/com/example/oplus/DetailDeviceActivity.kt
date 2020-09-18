package com.example.oplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.oplus.adapter.PropertiesDeviceAdapter
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.FarmDevice
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_detail_device.*

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
        inventoryViewModel?.getThuocTinhDong(device?.ListName ?: "", device?.ItemId ?: 0)
        propertiesDeviceAdapter = PropertiesDeviceAdapter(mutableListOf())
        rvProperties.layoutManager = GridLayoutManager(this, 1)
        rvProperties.setHasFixedSize(true)
        inventoryViewModel?.thuoctinh?.observe(this, {
            val listProperties = it?.Result?.ThuocTinh
            listProperties.let {
                propertiesDeviceAdapter?.setData(listProperties ?: mutableListOf())

            }
//            Glide.with(imgDevice.context).load(it?.Result?.Hinh).into(imgDevice)
            tvNameDevice.text = it?.Result?.Title
            viewpageAdapter = ViewpageImageDeviceAdater(supportFragmentManager, it?.Result?.Hinh)
            vpImgDevice.adapter = viewpageAdapter
            it?.Result?.apply {
                pivImg.count = Hinh?.size ?: 1
                pivImg.selection = 2
            }

        })


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
        rvProperties.adapter = propertiesDeviceAdapter
        ivExit.setOnClickListener {
            onBackPressed()
        }
    }
}