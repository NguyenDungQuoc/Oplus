package com.example.oplus.activities

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.DetailItemConfirmAdapter
import com.example.oplus.adapter.PropertiesDeviceAdapter
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.ItemConfirmInventory
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_detail_confirm_item.*
import kotlinx.android.synthetic.main.view_detail_item.*

class DetailConfirmItemActivity : AppCompatActivity() {
    private var itemAdater:PropertiesDeviceAdapter? = null
    private var item: ItemConfirmInventory? = null
    private var viewpageAdapter: ViewpageImageDeviceAdater? = null
    private var inventoryViewModel: InventoryViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_confirm_item)
        onClick()
        item = intent?.getParcelableExtra("DATA1")
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        item?.itemId?.let { inventoryViewModel?.chiTietMuaHang(it) }


        inventoryViewModel?.detailItemConfirm?.observe(this, {
            val listProperties = it?.result?.thuocTinh
            listProperties.let {
                itemAdater?.setData(listProperties ?: mutableListOf())
            }


            it?.result?.apply {
                tvNameDevice.text = title
                viewpageAdapter = ViewpageImageDeviceAdater(supportFragmentManager, it.result?.hinh)
                vpImgDevice.adapter = viewpageAdapter
                pivImg.count = 2
                pivImg.selection = 3
                imgDong.text = button?.get(0)?.title?.toUpperCase()
//                button?.get(0)?.MaMau?.toInt()?.let { it1 -> imgDong.setBackgroundColor(it1) }
                val color:Int = Color.parseColor(button?.get(0)?.maMau)
                    (imgDong.background as GradientDrawable).setColor(color)

            }

        })


        itemAdater = PropertiesDeviceAdapter(mutableListOf())
        rvProperties.layoutManager = GridLayoutManager(this, 1)
        rvProperties.setHasFixedSize(true)
        rvProperties.adapter = itemAdater
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