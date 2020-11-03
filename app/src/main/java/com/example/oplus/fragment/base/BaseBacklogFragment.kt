package com.example.oplus.fragment.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.BacklogAdapter
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.BaseTaskViewModel
import kotlinx.android.synthetic.main.fragment_backlog.*


abstract class BaseBacklogFragment(val tabName:String) : BaseFragment(R.layout.fragment_backlog) {

    private var backlogAdapter: BacklogAdapter? = null
    var request: TaskRequestDTO? = null
    var type =""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request?.tabName = tabName
        request?.ngay = ""
        request?.loTrong = 0

//        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)


        backlogAdapter = BacklogAdapter(mutableListOf())
        rvBacklog.layoutManager = LinearLayoutManager(activity)
        rvBacklog.setHasFixedSize(true)
        getViewModel().backlog?.observe(viewLifecycleOwner,{
            backlogAdapter?.insertData(it)
            rvBacklog.adapter = backlogAdapter
        })
    }
    abstract override fun getViewModel(): BaseTaskViewModel
}