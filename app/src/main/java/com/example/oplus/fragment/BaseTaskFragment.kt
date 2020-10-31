package com.example.oplus.fragment


import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.fragment.failure.BarCodeFragment
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.TabAdapter
import com.example.oplus.fragment.failure.BaseSearchFragment
import com.example.oplus.viewmodel.BaseTaskViewModel
import kotlinx.android.synthetic.main.fragment_base_task.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

abstract class BaseTaskFragment : BaseFragment(R.layout.fragment_base_task) {

    private var searchFragment : BaseSearchFragment? = null
    private var tabAdapter:TabAdapter? = null
    var barCodeFragment: BarCodeFragment? = null
    var type = ScreenIDEnum.QR_SCAN_FROM_FAILURE.value
    override fun initView() {
        defaultView()
        onClickEvent()
        tlTaskButton.withViewPager(vpTask)
        getViewModel().task.observe(viewLifecycleOwner, {
            tabAdapter = it?.let { it1 -> TabAdapter(childFragmentManager, it1) }
            if (it != null) {
                tlTaskButton.listHeader = it
            }
            hideLoading()
            vpTask.adapter = tabAdapter
        })
        getViewModel().errorMessage?.observe(viewLifecycleOwner,{
            hideLoading()
        })
    }
    abstract fun getViewModel():BaseTaskViewModel

    private fun defaultView() {
        tvTitleMenu.text = getTitle()
        fbScan.drawable.mutate()
            .setTint(ContextCompat.getColor(activity as MainActivity, R.color.white))
    }

    private fun onClickEvent() {
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        imgSearch.setOnClickListener {
            searchFragment = BaseSearchFragment()
            (activity as MainActivity).showFragment(searchFragment!!,true)
        }
        fbScan.setOnClickListener {
            barCodeFragment = BarCodeFragment()
            barCodeFragment?.type = type
            (activity as MainActivity).showFragment(barCodeFragment!!,true)
        }
    }

    abstract fun getTitle(): String?
}