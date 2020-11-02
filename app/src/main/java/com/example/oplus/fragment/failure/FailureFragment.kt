package com.example.oplus.fragment.failure

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.activities.MainActivity
import com.example.oplus.fragment.base.BaseBacklogFragment
import com.example.oplus.fragment.base.BaseSNCFragment
import com.example.oplus.fragment.crops.BacklogCropsFragment
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class FailureFragment : BaseSNCFragment() {
    private var failureViewModel: FailureViewModel? = null
    var typeS = ""
    var taskFailureFragment:TaskFailureFragment? = null

    override fun initView() {
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        failureViewModel?.soLuongCongViec()
        showLoading()
        listenerEvent()
        super.initView()
    }

    override fun getViewModel(): BaseTaskViewModel {
        return failureViewModel!!
    }

    override fun getTypeScreen(): String {
        return typeS
    }

    override fun getTitle(): String? {
        return "SỰ CỐ"
    }

    override fun getListFragmentInVP(list: MutableList<ResultTask>): MutableList<Fragment>? {
        listFragmentInVp = mutableListOf()
        taskFailureFragment = list.get(0).tabName?.let { TaskFailureFragment(it) }
        taskFailureFragment?.let { listFragmentInVp?.add(it) }
        backlogCropsFragment = list.get(1).tabName?.let { BacklogCropsFragment(it) }
        listFragmentInVp?.add(backlogCropsFragment!!)
        taskFailureFragment = list.get(2).tabName?.let { TaskFailureFragment(it) }
        taskFailureFragment?.let { listFragmentInVp?.add(it) }
        return listFragmentInVp
    }

    private fun listenerEvent() {
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}
