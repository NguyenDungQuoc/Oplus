package com.example.oplus.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.ConfirmInventoryAdapter
import com.example.oplus.adapter.StatusConfirmAdapter
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class ConfirmActivity : AppCompatActivity() {
    private var statusConfirmAdapter: StatusConfirmAdapter? = null
    private var inventoryViewModel: InventoryViewModel? = null
    private var confirmAdapter:ConfirmInventoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        inventoryViewModel?.demLichMuaHang(1, true)
        inventoryViewModel?.lichMuaTheoNgay("Sent")
        createRecycleView()
        menuTop()
        getWidthRecycleView()
        onClickEvent()


    }

    private fun onClickEvent() {
        statusConfirmAdapter?.onClick = {
            when(it?.tabName) {
                "ChoXacNhan" ->{
                    inventoryViewModel?.lichMuaTheoNgay("Sent")
                }
                "DaXacNhan" ->{
                    inventoryViewModel?.lichMuaTheoNgay("Approval")
                }
            }
        }

        confirmAdapter?.onClick = {
            val intent= Intent(this, DetailConfirmItemActivity::class.java)
            intent.putExtra("DATA1",it)
            startActivity(intent)
        }
    }

    private fun createRecycleView() {
        //Status Confirm
        rvStatusConfirm.layoutManager =
            GridLayoutManager(this, 2)
        rvStatusConfirm.setHasFixedSize(true)
        statusConfirmAdapter = StatusConfirmAdapter(mutableListOf())
        inventoryViewModel?.statusConfirm?.observe(this, {
            val listStatus = it?.result?.items
            listStatus?.getOrNull(0)?.isCheck = true
            listStatus.let {
                statusConfirmAdapter?.setData(listStatus)
            }
        })
        rvStatusConfirm.adapter = statusConfirmAdapter

        //Item
        rvListItemConfirm.layoutManager =
            GridLayoutManager(this, 1)
        rvListItemConfirm.setHasFixedSize(true)
        confirmAdapter = ConfirmInventoryAdapter((mutableListOf()))
        inventoryViewModel?.deviceConfirm?.observe(this, {
            val listItem = it?.result?.items
            listItem.let{
                confirmAdapter?.setData(listItem ?: mutableListOf())

            }
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

    private fun menuTop() {
        tvTitleMenu.text = "XÁC NHẬN LỊCH MUA"
        imgSearch.visibility = View.GONE
        imgBack.setOnClickListener {
            onBackPressed()
        }
    }

}