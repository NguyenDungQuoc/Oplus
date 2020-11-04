package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.giamsat.GiamSatItem
import com.example.oplus.model.giamsat.InfoItem
import com.example.oplus.model.giamsat.ResultItemForArea
import com.example.oplus.repository.GiamSatRepository

class GiamSatViewModel : BaseViewModel() {
    var giamSatRepository: GiamSatRepository = GiamSatRepository()
    var item:MutableLiveData<BaseResultItem<GiamSatItem>>? = MutableLiveData()
    var itemDetail:MutableLiveData<BaseResultItem<GiamSatItem>>? = MutableLiveData()
    var itemInfo:MutableLiveData<BaseResultItem<InfoItem>>? = MutableLiveData()
    var itemForArea:MutableLiveData<BaseResultItem<ResultItemForArea>>? = MutableLiveData()
    var workDay:MutableLiveData<MutableList<ResultDayWork>>? = MutableLiveData()

    fun getListHe(){
        giamSatRepository.getListHe({
               item?.value = it

        },{
            errorMessage?.value = it
        })
    }
    fun getListPhanKhu(iDHe:Int){
        giamSatRepository.getListPhanKhu(iDHe, {
            itemDetail?.value = it
        },{
            errorMessage?.value = it
        })
    }
    fun thongTinThietBi(){
        giamSatRepository.thongTinThietBi({
            itemInfo?.value = it
        },{
            errorMessage?.value = it
        })
    }
    fun getListThietBi(phanKhu:String){
        giamSatRepository.getListThietBi(phanKhu, {
            itemForArea?.value = it
        },{
            errorMessage?.value = it
        })
    }
    fun congViecTheoThietBi(itemID:String){
        giamSatRepository.congViecTheoThietBi(itemID, {
            workDay?.value = it?.items
        },{
            errorMessage?.value = it
        })
    }

}