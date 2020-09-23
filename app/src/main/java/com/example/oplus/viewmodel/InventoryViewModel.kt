package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.*
import com.example.oplus.repository.InventoryRepository

class InventoryViewModel : ViewModel() {
    var inventoryRepository: InventoryRepository = InventoryRepository()
    var resultStatus: MutableLiveData<BaseResponse<ResultStatusInventory>?>? = MutableLiveData()
    var farmDevice: MutableLiveData<BaseResponse<BaseResultItem<FarmDevice>>?>? = MutableLiveData()
    var thuoctinh: MutableLiveData<BaseResponse<ResultThuocTinhDong>?>? = MutableLiveData()
    var statusConfirm: MutableLiveData<BaseResponse<BaseResultItem<StatusConfirmInventory>>?>? =
        MutableLiveData()
    var deviceConfirm:MutableLiveData<BaseResponse<BaseResultItem<ItemConfirmInventory>>?>? =
        MutableLiveData()
    var detailItemConfirm:MutableLiveData<BaseResponse<ResultDetailBuy>>? = MutableLiveData()
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

    fun getThuocTinhDong(ListName: String, ItemId: Int) {
        inventoryRepository.getThuocTinhDong(ListName, ItemId, {
            thuoctinh?.value = it
        }, {
            errorMessage?.value = it
        })

    }

    fun demLichMuaHang(ID: Int, XacNhan: Boolean) {
        inventoryRepository.demLichMuaHang(ID, XacNhan, {
            statusConfirm?.value = it
        },{
            errorMessage?.value = it
        })
    }

    fun lichMuaTheoNgay(xacNhan:String){
        inventoryRepository.lichMuaHangTheoNgay(xacNhan,{
            deviceConfirm?.value = it
        },{
            errorMessage?.value = it
        })

    }

    fun chiTietMuaHang(id:Int){
        inventoryRepository.chiTietMuaHang(id, {
            detailItemConfirm?.value = it
        },{
            errorMessage?.value = it
        })
    }
}