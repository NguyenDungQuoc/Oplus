package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.BaseResponse
import com.example.oplus.model.ResultItemInventory
import com.example.oplus.model.ResultStatusInventory
import com.example.oplus.model.ResultThuocTinhDong
import com.example.oplus.repository.InventoryRepository

class InventoryViewModel : ViewModel() {
    var inventoryRepository: InventoryRepository = InventoryRepository()
    var resultStatus: MutableLiveData<BaseResponse<ResultStatusInventory>?>? = MutableLiveData()
    var farmDevice: MutableLiveData<BaseResponse<ResultItemInventory>?>? = MutableLiveData()
    var thuoctinh: MutableLiveData<BaseResponse<ResultThuocTinhDong>?>? = MutableLiveData()
    var errorMessage: MutableLiveData<String>? = MutableLiveData()

    fun getSoLuongTonKHo() {
        inventoryRepository.getSoLuongTonKho({
            resultStatus?.value = it
        }, {
            errorMessage?.value = it
        })
    }

    fun getDanhSachTonKho(IsEnd: Boolean, PageIndex: Int) {
        inventoryRepository.getDanhSachTonKho(IsEnd, PageIndex, {
            farmDevice?.value = it
        }, {
            errorMessage?.value = it
        })
    }

    fun getThuocTinhDong(ListName:String,ItemId:Int) {
        inventoryRepository.getThuocTinhDong(ListName,ItemId,{
            thuoctinh?.value = it
        },{
            errorMessage?.value=it
        })

    }
}