package com.example.oplus.activities

import android.content.Intent
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.adapter.ConfirmInventoryAdapter
import com.example.oplus.adapter.buttonUnderToolbar.ButtonUnderToolbarAdapter
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class ConfirmActivity : BaseActivity() {
    private var buttonUnderToolbarAdapter: ButtonUnderToolbarAdapter? = null
    private var inventoryViewModel: InventoryViewModel? = null
    private var confirmAdapter: ConfirmInventoryAdapter? = null
    override fun getBackImage(): View {
        return imgBack
    }

    override fun getResource(): Int {
        return R.layout.activity_confirm
    }

    override fun initView() {
        super.initView()
        showLoading()
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.demLichMuaHang(1, true)
        inventoryViewModel?.lichMuaTheoNgay("Sent")
        initToolbar(tvTitleMenu, getString(R.string.xac_nhan_lich_mua))
        imgSearch.visibility = View.GONE
        createRecycleView()
        getWidthRecycleView()
        onClickEvent()
    }

    private fun onClickEvent() {
        buttonUnderToolbarAdapter?.onClick = {
            when (it?.tabName) {
                getString(R.string.cho_xac_nhan) -> {
                    inventoryViewModel?.lichMuaTheoNgay("Sent")
                    showLoading()
                }
                getString(R.string.da_xac_nhan) -> {
                    inventoryViewModel?.lichMuaTheoNgay("Approval")
                    showLoading()
                }
            }
        }

        confirmAdapter?.onClick = {
            val intent = Intent(this, DetailConfirmItemActivity::class.java)
            intent.putExtra("DATA1", it)
            startActivity(intent)
        }
    }

    private fun createRecycleView() {
        //Status Confirm
        rvStatusConfirm.layoutManager =
            GridLayoutManager(this, 2)
        rvStatusConfirm.setHasFixedSize(true)
        buttonUnderToolbarAdapter = ButtonUnderToolbarAdapter(mutableListOf())
        inventoryViewModel?.statusConfirm?.observe(this, {
            val listStatus = it?.items
            listStatus?.getOrNull(0)?.isCheck = true
            listStatus.let {
                buttonUnderToolbarAdapter?.setData(listStatus)
            }
            hideLoading()
        })
        rvStatusConfirm.adapter = buttonUnderToolbarAdapter
        //Item
        rvListItemConfirm.layoutManager =
            GridLayoutManager(this, 1)
        rvListItemConfirm.setHasFixedSize(true)
        confirmAdapter = ConfirmInventoryAdapter((mutableListOf()))
        inventoryViewModel?.deviceConfirm?.observe(this, {
            val listItem = it?.items
            listItem.let {
                confirmAdapter?.setData(listItem ?: mutableListOf())
            }
            hideLoading()
        })
        rvListItemConfirm.adapter = confirmAdapter
    }

    private fun getWidthRecycleView() {
        rvStatusConfirm.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = rvStatusConfirm.width
//                statusConfirmAdapter?.parentWidth =width
                rvStatusConfirm.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

        })
    }

}