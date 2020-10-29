package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO
import com.example.oplus.repository.FailureRepository

class FailureViewModel : BaseViewModel() {
    var failureRepository: FailureRepository = FailureRepository()
    var task: MutableLiveData<MutableList<ResultTask>?> = MutableLiveData()
    var workDay:MutableLiveData<MutableList<ResultDayWork>> = MutableLiveData()
    var backlog:MutableLiveData<MutableList<ResultBacklogDTO>> = MutableLiveData()
    var item:MutableLiveData<ResultBaseDetail> = MutableLiveData()

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
    fun congViecTheoNgay1(tabName: String, ngay: String, loTrong: Int){
        failureRepository.congViecTheoNgay1(tabName,ngay,{
            backlog.value = it?.result?.items
        },{
            errorMessage?.value = it
        })
    }

    fun congViecTheoThang(rq:TaskRequestDTO){
        failureRepository.congViecTheoThang(rq,{
            workDay.value = it?.result?.items
        },{
            errorMessage?.value = it
        })
    }
    fun chiTietCongViec(iD: Int){
        failureRepository.chiTietCongViec(iD,{
            item.value = it?.result
        },{
            errorMessage?.value = it
        })

    }
}