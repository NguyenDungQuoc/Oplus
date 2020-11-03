package com.example.oplus.fragment.base

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.DayWorkAdapter
import com.example.oplus.extensions.selectedItemListener
import com.example.oplus.extensions.setList
import com.example.oplus.extensions.toSimpleString
import com.example.oplus.fragment.datepicker.MyDatePickerFragment
import com.example.oplus.model.base.Base
import com.example.oplus.model.inventory.Properties
import com.example.oplus.model.search.BaseSearchRequestDTO
import com.example.oplus.viewmodel.BaseViewModel
import com.example.oplus.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_base_search.*

import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

abstract class BaseSearchFragment : BaseFragment(R.layout.fragment_base_search) {
    var searchViewModel: SearchViewModel? = null
    private var dayWorkAdapter: DayWorkAdapter? = null
    var rq: BaseSearchRequestDTO? = null
    var propertyAt0: Properties? = null
    var defaultStt = ""
    var listStt : MutableList<Properties> = mutableListOf()

    override fun initView() {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        super.initView()
        searchViewModel?.layDanhSachTrangThai()
        showLoading()

        propertyAt0 = Properties().apply {
            title = "Tất cả"
            key = "Tất cả"
            value = "Tất cả"
        }
        defaultView()
        onClickEnvent()
        recyclerView()
        observer()
    }
    override fun getViewModel(): BaseViewModel {
       return  searchViewModel!!
    }
    abstract fun getTypeScreen():String
    private fun recyclerView() {
        dayWorkAdapter = DayWorkAdapter(mutableListOf())
        rvBaseSearch.layoutManager = LinearLayoutManager(activity)
        rvBaseSearch.setHasFixedSize(true)
        dayWorkAdapter?.type = getTypeScreen()
        rvBaseSearch.adapter = dayWorkAdapter
    }

    override fun onDestroy() {
        Base.loginData?.WebUrl = null
        super.onDestroy()

    }

    private fun observer() {
        searchViewModel?.status?.observe(viewLifecycleOwner, {
            propertyAt0?.let { it1 -> it.add(0, it1) }
            listStt = it
            spStatus.setList(it?.map { it.value }?.toMutableList(), 0)
            hideLoading()
        })

        searchViewModel?.result?.observe(viewLifecycleOwner, {
            it?.items?.let { it1 -> dayWorkAdapter?.insertData(it1) }
        })
    }

    private fun onClickEnvent() {
        tvFromDate.setOnClickListener {
            fragmentManager?.let { it1 ->
                MyDatePickerFragment.showPicker(it1, 0).setResultListener {
                    tvFromDate.text = it.toSimpleString()
                }
            }
        }
        tvToDate.setOnClickListener {
            fragmentManager?.let { it1 ->
                MyDatePickerFragment.showPicker(it1, 0).setResultListener {
                    tvToDate.text = it.toSimpleString()
                }
            }
        }

        spStatus.selectedItemListener{
            if(it == 0 ){
                defaultStt = ""
            }else{
                defaultStt = listStt.get(it).value.toString()
            }
        }

        tvSearch.setOnClickListener {
            rq = BaseSearchRequestDTO().apply {
                key = edtBaseSearch.text.toString()
                tuNgay = checkFromDate()
                denNgay = checkToDate()
                trangThai = defaultStt
                pagingInfo = null
            }
            searchViewModel?.timcongviec(rq ?: BaseSearchRequestDTO())
        }

        ivBaseClear.setOnClickListener {
            edtBaseSearch.text?.clear()
        }
        edtBaseSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length == 0) {
                    ivBaseClear.visibility = View.GONE
                } else {
                    ivBaseClear.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun defaultView() {
        ivBaseClear.visibility = View.GONE
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        tvTitleMenu.text = getString(R.string.tim_kiem).toUpperCase()
        imgSearch.visibility = View.GONE
    }

    fun checkToDate(): String {
        if (tvToDate.text.toString() == getString(R.string.to_date)) {
            return ""
        } else {
            return tvToDate.text.toString()
        }
    }

    fun checkFromDate(): String {
        if (tvToDate.text.toString() == getString(R.string.from_date)) {
            return ""
        } else {
            return tvFromDate.text.toString()
        }
    }

}