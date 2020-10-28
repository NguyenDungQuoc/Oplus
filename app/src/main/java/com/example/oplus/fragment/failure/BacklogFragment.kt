package com.example.oplus.fragment.failure

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.BacklogAdapter
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.fragment_backlog.*


class BacklogFragment : Fragment(R.layout.fragment_backlog) {
    private var failureViewModel: FailureViewModel? = null
    private var backlogAdapter: BacklogAdapter? = null
    var request: TaskRequestDTO? = TaskRequestDTO()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request?.tabName = "TonDong"
        request?.ngay = ""
        request?.LoTrong = 0
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        failureViewModel?.congViecTheoNgay1(request?.tabName!!, request?.ngay!!, request?.LoTrong!!)
        backlogAdapter = BacklogAdapter(mutableListOf())
        rvBacklog.layoutManager = LinearLayoutManager(activity)
        rvBacklog.setHasFixedSize(true)
        rvBacklog.adapter = backlogAdapter
        failureViewModel?.backlog?.observe(viewLifecycleOwner,{
            backlogAdapter?.insertData(it)
        })
    }
}