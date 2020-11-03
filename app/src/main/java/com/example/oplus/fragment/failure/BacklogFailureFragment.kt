package com.example.oplus.fragment.failure

import androidx.lifecycle.ViewModelProviders
import com.example.oplus.fragment.base.BaseBacklogFragment
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.FailureViewModel

class BacklogFailureFragment(tabNameChild:String) : BaseBacklogFragment(tabNameChild)  {
    private var failureViewModel: FailureViewModel? = null

    override fun initView() {
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        super.initView()
        request= TaskRequestDTO()
        request?.tabName = tabName
        request?.ngay = ""
        request?.loTrong = 0
        failureViewModel?.congViecTheoNgay1(request?.tabName!!, request?.ngay!!, request?.loTrong!!)
    }
    override fun getViewModel(): BaseTaskViewModel {
        return  failureViewModel!!
    }
}