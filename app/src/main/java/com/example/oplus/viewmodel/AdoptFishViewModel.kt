package com.example.oplus.viewmodel

import com.example.oplus.repository.AdoptFishRepository

class AdoptFishViewModel : BaseTaskViewModel() {
    private var adoptFishRepository:AdoptFishRepository = AdoptFishRepository()

    fun soLuongCongViec() {
        adoptFishRepository.soLuongCongViec({
            task.value = it?.items
        }, {
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay(tabName: String, ngay: String, loNuoi: Int){
        adoptFishRepository.congViecTheoNgay(tabName,ngay,loNuoi,{
            workDay.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay1(tabName: String, ngay: String, loNuoi: Int){
        adoptFishRepository.congViecTheoNgay1(tabName,ngay,loNuoi,{
            backlog.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
}