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
    fun congViecTheoNgay(tabName: String, ngay: String, loTrong: Int){
        adoptFishRepository.congViecTheoNgay(tabName,ngay,loTrong,{
            workDay.value = it?.items
        },{
            errorMessage?.value = it
        })
    }
}