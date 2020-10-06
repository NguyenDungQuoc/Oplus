package com.example.oplus.fragment.inventory

import android.content.Intent
import android.os.Bundle
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
import com.example.oplus.fragment.BaseFragment
import com.example.oplus.model.base.Base
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.fragment_inventory.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import java.util.*


class InventoryFragment : BaseFragment(R.layout.fragment_inventory) {
    private var inventoryViewModel: InventoryViewModel? = null
    private var inventoryAdapter: InventoryAdapter? = null
    var pageIndex:Int = 1
    var isDaHet = true
    private var searchFragment: SearchFragment = SearchFragment()

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
        tvTitleMenu.text = getString(R.string.ton_kho)
        daHetActive(true)
    }

    private fun viewModelObserse() {
        inventoryViewModel?.resultStatus?.observe(viewLifecycleOwner, {
            Base.statusStatusInventory = it?.result
            Base.statusStatusInventory?.apply {
                tvDaHet.text = daHet?.title?.toUpperCase(Locale.ROOT)
                tvNumberDaHet.text = daHet?.value
                tvSapHet.text = sapHet?.title?.toUpperCase(Locale.ROOT)
                tvNumberSapHet.text = sapHet?.value
                tvLichMua.text = lichMua?.title?.toUpperCase(Locale.ROOT)
                tvNumberLichMua.text = lichMua?.value
                tvChoXacNhan.text = choXacNhan?.title?.toUpperCase(Locale.ROOT)
                tvNumberChoXacNhan.text = choXacNhan?.value
            }
            loadingDialog?.hide()
        })
        inventoryViewModel?.farmDevice?.observe(viewLifecycleOwner, {
            val listDevice = it?.result?.items
            listDevice?.let {
                inventoryAdapter?.insertData(listDevice)
            }
            loadingDialog?.hide()
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
            loadingDialog?.show()
            checkActive(true)
        }
        ctSapHet.setOnClickListener {
            loadingDialog?.show()
            checkActive(false)
        }
        ctChoXacNhan.setOnClickListener {
            val intent = Intent(activity, ConfirmActivity::class.java)
            startActivity(intent)
        }
        imgSearch.setOnClickListener {
            (activity as MainActivity).showFragment(searchFragment,true)
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
    private fun checkActive(status:Boolean){
        isDaHet = status
        inventoryAdapter?.clearData()
        pageIndex = 1
        inventoryViewModel?.getDanhSachTonKho(status, pageIndex)
        sapHetActive(!status)
        daHetActive(status)
    }
}