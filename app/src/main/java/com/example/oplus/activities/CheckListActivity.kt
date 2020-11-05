package com.example.oplus.activities

import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.adapter.ItemCheckListAdapter
import com.example.oplus.fragment.crops.CustomDialogCheckListFragment
import com.example.oplus.model.crop.ResultCheckListDTO
import com.example.oplus.viewmodel.CropsViewModel
import kotlinx.android.synthetic.main.activity_checklist.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class CheckListActivity : BaseActivity() {
    var cropsViewModel: CropsViewModel? = null
    var id = 0
    var title = ""
    var mAdapter: ItemCheckListAdapter? = null
    var customDialogCheckListFragment:CustomDialogCheckListFragment? =null
    override fun initView() {
        imgBack.setImageResource(R.drawable.icon_exit)
        super.initView()
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        defaultView()
        showLoading()
//        materialButton.setBackgroundColor(resources.getColor(R.color.red))
        id = intent.getIntExtra("ID_ITEM", 1)
        title = intent.getStringExtra("TITLE").toString()
        cropsViewModel?.danhSachCheckList(id)
        recyclerView()
        observer()
        onClickEvent()
    }

    private fun onClickEvent() {
        mAdapter?.onClick = {
            customDialogCheckListFragment = CustomDialogCheckListFragment()
            customDialogCheckListFragment?.listVatTu = it?.listVatTu
            customDialogCheckListFragment?.show(supportFragmentManager,"String")
        }
    }

    private fun recyclerView() {
        mAdapter = ItemCheckListAdapter(mutableListOf())
        rvCheckList.layoutManager = LinearLayoutManager(this)
        rvCheckList.setHasFixedSize(true)
        rvCheckList.adapter = mAdapter
    }

    private fun observer() {
        cropsViewModel?.resultCheckList?.observe(this, {
            it?.items?.let { it1 -> mAdapter?.insertData(it1) }
            if (it != null) {
                bindingData(it)
            }
            hideLoading()
        })
    }

    fun bindingData(item: ResultCheckListDTO) {
        item.titleForm?.let { initToolbar(tvTitleMenu, it) }
        tvNameItem.text = title
        tvSTTCL.text= item.header?.get(0)
        tvHMCL.text= item.header?.get(1)
        tvDLCL.text= item.header?.get(2)
        btXacNhan.text = item.buttons?.get(0)?.title
        val color: Int = Color.parseColor(item.buttons?.get(0)?.maMau)
        btXacNhan.setBackgroundColor(color)

        if (item.buttons?.get(0)?.enable!!){
            btXacNhan.isEnabled = true
        }else{
            btXacNhan.alpha = 0.5f
            btXacNhan.isEnabled = false
        }



        btBaoCao.text = item.buttons?.get(1)?.title
        val color2: Int = Color.parseColor(item.buttons?.get(1)?.maMau)
        btBaoCao.setBackgroundColor(color2)
        if (item.buttons?.get(1)?.enable!!){
            btBaoCao.isEnabled = true
        }else{
            btBaoCao.alpha = 0.5f
            btBaoCao.isEnabled = false
        }
    }

    private fun defaultView() {

        imgSearch.visibility = View.GONE
    }

    override fun getResource(): Int {
        return R.layout.activity_checklist
    }

    override fun getBackImage(): View? {
        return imgBack
    }
}