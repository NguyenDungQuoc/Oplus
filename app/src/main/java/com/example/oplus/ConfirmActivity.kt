package com.example.oplus

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.adapter.StatusConfirmAdapter
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class ConfirmActivity : AppCompatActivity() {
    private var statusConfirmAdapter: StatusConfirmAdapter? = null
    private var inventoryViewModel: InventoryViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        createRecycleView()

        menuTop()
        getWidthRecycleView()
        statusConfirmAdapter?.onClick = {

        }
    }

    private fun createRecycleView() {
        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.demLichMuaHang(1, true)
        rvStatusConfirm.layoutManager =
            GridLayoutManager(this, 2)
        rvStatusConfirm.setHasFixedSize(true)
        statusConfirmAdapter = StatusConfirmAdapter(mutableListOf())
        inventoryViewModel?.statusConfirm?.observe(this, {
            val listStatus = it?.Result?.Items
            listStatus?.getOrNull(0)?.isCheck = true
            listStatus.let {
                statusConfirmAdapter?.setData(listStatus)
            }
        })
        rvStatusConfirm.adapter = statusConfirmAdapter
    }

    private fun getWidthRecycleView() {
        rvStatusConfirm.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = rvStatusConfirm.width
                rvStatusConfirm.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

        })
    }

    private fun menuTop() {
        tvTitleMenu.text = "XÁC NHẬN LỊCH MUA"
        imgSearch.visibility = View.GONE
        imgBack.setOnClickListener {
            onBackPressed()
        }
    }

}