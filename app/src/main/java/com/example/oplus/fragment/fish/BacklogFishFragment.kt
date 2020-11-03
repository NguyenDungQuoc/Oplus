package com.example.oplus.fragment.fish

import androidx.lifecycle.ViewModelProviders
import com.example.oplus.fragment.base.BaseBacklogFragment
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.AdoptFishViewModel

import com.example.oplus.viewmodel.BaseTaskViewModel

class BacklogFishFragment(tabNameChild: String) : BaseBacklogFragment(tabNameChild) {
    var adoptFishViewModel: AdoptFishViewModel? = null
    override fun initView() {
        adoptFishViewModel = ViewModelProviders.of(this).get(AdoptFishViewModel::class.java)
        super.initView()
        request= TaskRequestDTO()
        request?.tabName = tabName
        request?.ngay = ""
        request?.loNuoi = 0

        adoptFishViewModel?.congViecTheoNgay1(request?.tabName!!, request?.ngay!!, request?.loNuoi!!)
    }

    override fun getViewModel(): BaseTaskViewModel {
        return adoptFishViewModel!!
    }
}