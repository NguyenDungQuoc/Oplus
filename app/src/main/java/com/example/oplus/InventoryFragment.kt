package com.example.oplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.adapter.InventoryAdapter
import com.example.oplus.model.Base
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.fragment_inventory.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class InventoryFragment : Fragment(R.layout.fragment_inventory) {
    private var inventoryViewModel: InventoryViewModel? = null
    private var inventoryAdapter: InventoryAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecycleView()
        setOnClickListener()
        inventoryAdapter?.onClick = {

            val intent= Intent(activity,DetailDeviceActivity::class.java)
            intent.putExtra("DATA",it)
            startActivity(intent)
        }

    }

    private fun createRecycleView() {
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.getSoLuongTonKHo()

        inventoryViewModel?.resultStatus?.observe(viewLifecycleOwner, {
            Base.statusStatusInventory = it?.Result
            Base.statusStatusInventory?.apply {
                tvDaHet.text = DaHet?.Title?.toUpperCase()
                tvNumberDaHet.text = DaHet?.Value
                tvSapHet.text = SapHet?.Title?.toUpperCase()
                tvNumberSapHet.text = SapHet?.Value
                tvLichMua.text = LichMua?.Title?.toUpperCase()
                tvNumberLichMua.text = LichMua?.Value
                tvChoXacNhan.text = ChoXacNhan?.Title?.toUpperCase()
                tvNumberChoXacNhan.text = ChoXacNhan?.Value

            }
        })
        inventoryAdapter = InventoryAdapter(mutableListOf())
        rvListDevice.layoutManager = GridLayoutManager(activity, 1)
        rvListDevice.setHasFixedSize(true)
        inventoryViewModel?.farmDevice?.observe(viewLifecycleOwner, {
            val listDevice = it?.Result?.Items
            listDevice?.let {
                inventoryAdapter?.setData(listDevice)
            }
        })
        rvListDevice.adapter = inventoryAdapter
    }

    private fun setOnClickListener() {
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        inventoryViewModel?.getDanhSachTonKho(true, 1)
        daHetActive(true)
        ctDaHet.setOnClickListener {
            inventoryViewModel?.getDanhSachTonKho(true, 1)
            SapHetActive(false)
            daHetActive(true)
        }
        ctSapHet.setOnClickListener {
            inventoryViewModel?.getDanhSachTonKho(false, 1)
            SapHetActive(true)
            daHetActive(false)
        }
    }

    private fun daHetActive(isActive: Boolean) {
        ctDaHet.isSelected = isActive
        imgDaHet.isSelected = isActive
    }

    private fun SapHetActive(isActive: Boolean) {
        ctSapHet.isSelected = isActive
        imgSapHet.isSelected = isActive
    }
}