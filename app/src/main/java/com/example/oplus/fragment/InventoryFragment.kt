package com.example.oplus.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.activities.ConfirmActivity
import com.example.oplus.activities.DetailDeviceActivity
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.InventoryAdapter
import com.example.oplus.model.Base
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.fragment_inventory.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class InventoryFragment : Fragment(R.layout.fragment_inventory) {
    private var inventoryViewModel: InventoryViewModel? = null
    private var inventoryAdapter: InventoryAdapter? = null
    var pageIndex:Int = 1
    var isDaHet = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.getSoLuongTonKHo()
        inventoryViewModel?.getDanhSachTonKho(true, pageIndex)

        defaultView()
        createRecycleView()
        setOnClickListener()

        viewModelObserse()
    }

    private fun defaultView() {
        tvTitleMenu.text = "TỒN KHO"
        daHetActive(true)
    }

    private fun viewModelObserse() {
        inventoryViewModel?.resultStatus?.observe(viewLifecycleOwner, {
            Base.statusStatusInventory = it?.result
            Base.statusStatusInventory?.apply {
                tvDaHet.text = daHet?.title?.toUpperCase()
                tvNumberDaHet.text = daHet?.value
                tvSapHet.text = sapHet?.title?.toUpperCase()
                tvNumberSapHet.text = sapHet?.value
                tvLichMua.text = lichMua?.title?.toUpperCase()
                tvNumberLichMua.text = lichMua?.value
                tvChoXacNhan.text = choXacNhan?.title?.toUpperCase()
                tvNumberChoXacNhan.text = choXacNhan?.value

            }
        })
        inventoryViewModel?.farmDevice?.observe(viewLifecycleOwner, {
            val listDevice = it?.result?.items
            listDevice?.let {
                inventoryAdapter?.setData(listDevice)
            }
        })

    }

    private fun createRecycleView() {

        inventoryAdapter = InventoryAdapter(mutableListOf())
        rvListDevice.layoutManager = GridLayoutManager(activity, 1)
        rvListDevice.setHasFixedSize(true)

        rvListDevice.adapter = inventoryAdapter
        rvListDevice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        pageIndex += 1
                        inventoryViewModel?.getDanhSachTonKho(isDaHet, pageIndex )


                }
            }
        })
    }

    private fun setOnClickListener() {

        inventoryAdapter?.onClick = {
            val intent= Intent(activity, DetailDeviceActivity::class.java)
            intent.putExtra("DATA", it)
            startActivity(intent)
        }

        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }

        ctDaHet.setOnClickListener {
            isDaHet = true
            inventoryAdapter?.clearData()
            pageIndex = 1
            inventoryViewModel?.getDanhSachTonKho(true, pageIndex)
            sapHetActive(false)
            daHetActive(true)
        }
        ctSapHet.setOnClickListener {
            isDaHet = false
            inventoryAdapter?.clearData()
            pageIndex = 1
            inventoryViewModel?.getDanhSachTonKho(false, pageIndex)
            sapHetActive(true)
            daHetActive(false)
        }
        ctChoXacNhan.setOnClickListener {
            val intent = Intent(activity, ConfirmActivity::class.java)
            startActivity(intent)
        }
    }

    private fun daHetActive(isActive: Boolean) {
        ctDaHet.isSelected = isActive
        imgDaHet.isSelected = isActive
    }

    private fun sapHetActive(isActive: Boolean) {
        ctSapHet.isSelected = isActive
        imgSapHet.isSelected = isActive
    }
}