package com.example.oplus.viewmodel

import com.example.oplus.repository.CropsRepository

class CropsViewModel : BaseTaskViewModel() {
    private var cropsRepository:CropsRepository = CropsRepository()

    fun soLuongCongViec() {
        cropsRepository.soLuongCongViec({
            task.value = it?.items
        }, {
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay(tabName: String, ngay: String, loTrong: Int){
        cropsRepository.congViecTheoNgay(tabName,ngay,loTrong,{
            workDay.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

    fun congViecTheoNgay1(tabName: String, ngay: String, loTrong: Int){
        cropsRepository.congViecTheoNgay1(tabName,ngay,loTrong,{
            backlog.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
}