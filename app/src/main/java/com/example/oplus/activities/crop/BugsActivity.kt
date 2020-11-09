package com.example.oplus.activities.crop

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.adapter.BugsAdapter
import com.example.oplus.customview.VNCharacterUtils
import com.example.oplus.model.crop.VatTuDTO
import com.example.oplus.viewmodel.CropsViewModel
import kotlinx.android.synthetic.main.activity_bugs.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class BugsActivity : BaseActivity() {
    var bugsAdapter: BugsAdapter? = null
    var cropsViewModel: CropsViewModel? = null
    override fun initView() {
        super.initView()
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        cropsViewModel?.layDanhSachSauHai()
        defaultView()
        recyclerView()
        observer()

    }

    private fun recyclerView() {
        bugsAdapter = BugsAdapter(mutableListOf())
        rvBug.layoutManager = LinearLayoutManager(this)
        rvBug.setHasFixedSize(true)
        rvBug.adapter = bugsAdapter
    }

    private fun observer() {
        cropsViewModel?.bugs?.observe(this, {
            it?.items?.let { it1 -> bugsAdapter?.bindingData(it1) }



            edtSearchBug.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val listBug = mutableListOf<VatTuDTO>()
                    val listBug2 = mutableListOf<VatTuDTO>()
//                    val listBugFilter = it?.items?.filter {
//                        it.title?.toLowerCase() == p0.toString().toLowerCase()
//                    } as MutableList<VatTuDTO>

                    it?.items?.filterTo(listBug) {
                        VNCharacterUtils.removeAccent(it.title)
                            .toLowerCase() == VNCharacterUtils.removeAccent(p0.toString())
                            .toLowerCase()
                    }
                    it?.items?.filterTo(listBug2) {
                        it.title?.substring(0, 1)?.toLowerCase() == p0?.substring(0, 1)
                            ?.toLowerCase()
                    }
                    when (edtSearchBug.text.length) {
                        0 -> {
                            it?.items?.let { it1 -> bugsAdapter?.bindingData(it1) }
                        }
                        1 -> {
                            bugsAdapter?.bindingData(listBug2)
                        }
                        else -> {
                            bugsAdapter?.bindingData(listBug)
                        }
                    }
                }

            })
        })
    }

    private fun defaultView() {
        imgSearch.visibility = View.GONE
        initToolbar(tvTitleMenu, "DANH SÁCH SÂU")
    }

    override fun getResource(): Int {
        return R.layout.activity_bugs
    }

    override fun getBackImage(): View? {
        return imgBack
    }

}