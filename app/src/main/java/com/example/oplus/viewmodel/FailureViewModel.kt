package com.example.oplus.viewmodel

import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.repository.FailureRepository

class FailureViewModel : BaseTaskViewModel() {
    private var failureRepository: FailureRepository = FailureRepository()
    fun soLuongCongViec() {
        failureRepository.soLuongCongViec({
            task.value = it?.items
        }, {
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay(tabName:String,ngay: String){
        failureRepository.congViecTheoNgay(tabName,ngay,{
            workDay?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay1(tabName: String, ngay: String, loTrong: Int){
        failureRepository.congViecTheoNgay1(tabName,ngay,{
            backlog?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

    fun congViecTheoThang(rq:TaskRequestDTO){
        failureRepository.congViecTheoThang(rq,{
            workDay?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
    fun chiTietCongViec(iD: Int){
        failureRepository.chiTietCongViec(iD,{
            item.value = it
        },{
            errorMessage?.value = it
        })

    }
    fun laySuCoCuaThietBi(iD: Int){
        failureRepository.laySuCoCuaThietBi(iD,{
            item.value = it
        },{
            errorMessage?.value = it
        })

    }
}