package com.example.oplus.fragment.crops

import androidx.lifecycle.ViewModelProviders
import com.example.oplus.fragment.base.BaseBacklogFragment
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel


class BacklogCropsFragment(tabNameChild: String) : BaseBacklogFragment(tabNameChild) {
    var cropsViewModel: CropsViewModel? = null
    override fun initView() {
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        super.initView()
        request = TaskRequestDTO()
        request?.tabName = tabName
        request?.ngay = ""
        request?.loTrong = 0

        cropsViewModel?.congViecTheoNgay1(request?.tabName!!, request?.ngay!!, request?.loTrong!!)

    }

    override fun getViewModel(): BaseTaskViewModel {
        return cropsViewModel!!
    }
}