package com.example.oplus.fragment.base


import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.ViewPagerAdapter
import com.example.oplus.fragment.crops.BacklogCropsFragment
import com.example.oplus.fragment.failure.BarCodeFragment
import com.example.oplus.fragment.failure.BaseSearchFragment
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.viewmodel.BaseTaskViewModel
import kotlinx.android.synthetic.main.fragment_base_task.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

abstract class BaseSNCFragment : BaseFragment(R.layout.fragment_base_task) {
    var backlogCropsFragment: BacklogCropsFragment? =null
    private var searchFragment: BaseSearchFragment? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null
    var barCodeFragment: BarCodeFragment? = null
    var type = ScreenIDEnum.FAILURE_SCREEN.value
    var listFragmentInVp: MutableList<Fragment>? = null
    override fun initView() {
        defaultView()
        onClickEvent()
        tlTaskButton.withViewPager(vpTask)

        getViewModel().task.observe(viewLifecycleOwner, {

            if (it != null) {
                tlTaskButton.listHeader = it
            }
            viewPagerAdapter = it?.let { it1 -> ViewPagerAdapter(childFragmentManager, getListFragmentInVP(it) ?: mutableListOf()) }
            viewPagerAdapter?.type = getTypeScreen()
            vpTask.adapter = viewPagerAdapter
            hideLoading()

        })
        getViewModel().errorMessage?.observe(viewLifecycleOwner, {
            hideLoading()
        })
    }
    open fun getListFragmentInVP(list:MutableList<ResultTask>) : MutableList<Fragment>? {
        return null
    }

    abstract fun getViewModel(): BaseTaskViewModel

    abstract fun getTypeScreen(): String

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
            (activity as MainActivity).showFragment(searchFragment!!, true)
        }
        fbScan.setOnClickListener {
            barCodeFragment = BarCodeFragment()
            barCodeFragment?.type = type
            (activity as MainActivity).showFragment(barCodeFragment!!, true)
        }
    }

    abstract fun getTitle(): String?
}