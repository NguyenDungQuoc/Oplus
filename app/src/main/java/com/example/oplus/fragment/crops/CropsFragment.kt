package com.example.oplus.fragment.crops

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.fragment.base.BaseSNCFragment
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel
import kotlinx.android.synthetic.main.fragment_base_task.*

class CropsFragment : BaseSNCFragment() {
    var cropsViewModel: CropsViewModel? = null
    var taskCropsFragment: TaskCropsFragment? = null

    var typeS = ""
    override fun initView() {
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        cropsViewModel?.soLuongCongViec()
        showLoading()
        super.initView()
        fbScan.visibility = View.GONE
    }

    override fun getViewModel(): BaseTaskViewModel {
        return cropsViewModel!!
    }

    override fun getTypeScreen(): String {
        return typeS
    }

    override fun getTitle(): String? {
        return getString(R.string.crops).toUpperCase()
    }

    override fun getListFragmentInVP(list: MutableList<ResultTask>): MutableList<Fragment>? {
        listFragmentInVp = mutableListOf()
        taskCropsFragment = list.get(0).tabName?.let { TaskCropsFragment(it) }
        taskCropsFragment?.let { listFragmentInVp?.add(it) }
        backlogCropsFragment = list.get(1).tabName?.let { BacklogCropsFragment(it) }
        listFragmentInVp?.add(backlogCropsFragment!!)
        taskCropsFragment = list.get(2).tabName?.let { TaskCropsFragment(it) }
        taskCropsFragment?.let { listFragmentInVp?.add(it) }
        return listFragmentInVp
    }
}