package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.giamsat.GiamSatItem
import com.example.oplus.model.giamsat.InfoItem
import com.example.oplus.model.giamsat.ResultItemForArea
import com.example.oplus.repository.GiamSatRepository

class GiamSatViewModel : BaseViewModel() {
    var giamSatRepository: GiamSatRepository = GiamSatRepository()
    var item:MutableLiveData<BaseResponse<BaseResultItem<GiamSatItem>>>? = MutableLiveData()
    var itemDetail:MutableLiveData<BaseResponse<BaseResultItem<GiamSatItem>>>? = MutableLiveData()
    var itemInfo:MutableLiveData<BaseResponse<BaseResultItem<InfoItem>>>? = MutableLiveData()
    var itemForArea:MutableLiveData<BaseResponse<BaseResultItem<ResultItemForArea>>>? = MutableLiveData()

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
}