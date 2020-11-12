package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.oplus.model.crop.ResultCheckListDTO
import com.example.oplus.repository.CropsRepository

class CropsViewModel : BaseTaskViewModel() {
    private var cropsRepository:CropsRepository = CropsRepository()
    var resultCheckList:MutableLiveData<ResultCheckListDTO?>? = MutableLiveData()


    fun soLuongCongViec() {
        cropsRepository.soLuongCongViec({
            task.value = it?.items
        }, {
            errorMessage?.value = it
        })
    }
    fun congViecTheoNgay(tabName: String, ngay: String, loTrong: Int){
        cropsRepository.congViecTheoNgay(tabName,ngay,loTrong,{
            workDay?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

    fun congViecTheoNgay1(tabName: String, ngay: String, loTrong: Int){
        cropsRepository.congViecTheoNgay1(tabName,ngay,loTrong,{
            backlog?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

    fun danhSachCheckList(iD:Int){
        cropsRepository.danhSachCheckList(iD,{
            resultCheckList?.value = it
        },{
            errorMessage?.value = it
        })
    }
    fun getDanhSachCumLo(){
        cropsRepository.getDanhSachCumLo({
            cluster?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

    fun getChiTietLichLamViec(iD:Int){
        cropsRepository.getChiTietLichLamViec(iD,{
            detailWork?.value = it
        },{
            errorMessage?.value = it
        })
    }

    fun layDanhSachSauHai(){
        cropsRepository.layDanhSachSauHai({
            bugs?.value = it
        },{
            errorMessage?.value = it
        })
    }
    fun layChiTietSauHai(iD:Int){
        cropsRepository.layChiTietSauHai(iD,{
            propertiesBug?.value = it
        },{
            errorMessage?.value = it
        })
    }
}