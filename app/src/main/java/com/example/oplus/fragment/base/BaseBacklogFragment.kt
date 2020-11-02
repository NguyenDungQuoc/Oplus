package com.example.oplus.fragment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.BacklogAdapter
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.fragment_backlog.*


abstract class BaseBacklogFragment(val tabName:String) : BaseFragment(R.layout.fragment_backlog) {
    private var failureViewModel: FailureViewModel? = null
    private var backlogAdapter: BacklogAdapter? = null
    var request: TaskRequestDTO? = null
    var type =""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request?.tabName = tabName
        request?.ngay = ""
        request?.LoTrong = 0
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
//        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
//        failureViewModel?.congViecTheoNgay1(request?.tabName!!, request?.ngay!!, request?.LoTrong!!)

        backlogAdapter = BacklogAdapter(mutableListOf())
        rvBacklog.layoutManager = LinearLayoutManager(activity)
        rvBacklog.setHasFixedSize(true)
        getViewModel().backlog?.observe(viewLifecycleOwner,{
            backlogAdapter?.insertData(it)
            rvBacklog.adapter = backlogAdapter
        })
    }
    abstract fun getViewModel(): BaseTaskViewModel
}