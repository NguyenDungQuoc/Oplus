package com.example.oplus.activities

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.PropertiesDeviceAdapter
import com.example.oplus.adapter.ViewpageImageDeviceAdater
import com.example.oplus.model.inventory.ItemConfirmInventory
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_detail_confirm_item.*
import kotlinx.android.synthetic.main.view_detail_item.*
import java.util.*

class DetailConfirmItemActivity : BaseActivity() {
    private var itemAdapter: PropertiesDeviceAdapter? = null
    private var item: ItemConfirmInventory? = null
    private var viewpagerAdapter: ViewpageImageDeviceAdater? = null
    private var inventoryViewModel: InventoryViewModel? = null
    override fun getBackImage(): View {
        return ivExit
    }

    override fun initView() {
        super.initView()
        item = intent?.getParcelableExtra("DATA1")
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        showLoading()
        item?.itemId?.let { inventoryViewModel?.chiTietMuaHang(it) }
        onClick()
        observe()
        createRecycleView()
    }

    override fun getResource(): Int {
        return R.layout.activity_detail_confirm_item
    }

    private fun createRecycleView() {
        itemAdapter = PropertiesDeviceAdapter(mutableListOf())
        rvProperties.layoutManager = GridLayoutManager(this, 1)
        rvProperties.setHasFixedSize(true)
        rvProperties.adapter = itemAdapter
    }

    private fun observe() {
        inventoryViewModel?.detailItemConfirm?.observe(this, {
            val listProperties = it?.thuocTinh
            listProperties.let {
                itemAdapter?.setData(listProperties ?: mutableListOf())
            }
            it?.apply {
                tvNameDevice.text = title
                viewpagerAdapter =
                    ViewpageImageDeviceAdater(supportFragmentManager, it.hinh)
                vpImgDevice.adapter = viewpagerAdapter
                pivImg.count = 2
                pivImg.selection = 2
                imgDong.text = button?.get(0)?.title?.toUpperCase(Locale.ROOT)
//                button?.get(0)?.MaMau?.toInt()?.let { it1 -> imgDong.setBackgroundColor(it1) }
                val color: Int = Color.parseColor(button?.get(0)?.maMau)
                (imgDong.background as GradientDrawable).setColor(color)
            }
           hideLoading()
        })
    }

    private fun onClick() {
//        ivExit.setOnClickListener {
//            onBackPressed()
//        }
        imgDong.setOnClickListener {
            onBackPressed()
        }
    }
}