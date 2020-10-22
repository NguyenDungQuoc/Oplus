package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.repository.FailureRepository
import java.util.*

class FailureViewModel : ViewModel() {
    var failureRepository: FailureRepository = FailureRepository()
    var task: MutableLiveData<MutableList<ResultTask>> = MutableLiveData()
    var workDay:MutableLiveData<MutableList<ResultDayWork>> = MutableLiveData()
    var errorMessage: MutableLiveData<String>? = MutableLiveData()


    fun soLuongCongViec() {
        failureRepository.soLuongCongViec({
            task.value = it?.result?.items
        }, {
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay(tabName:String,ngay: String){
        failureRepository.congViecTheoNgay(tabName,ngay,{
            workDay.value = it?.result?.items
        },{
            errorMessage?.value = it
        })
    }

}