package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.*
import com.example.oplus.repository.InventoryRepository

class InventoryViewModel : BaseViewModel() {
    var inventoryRepository: InventoryRepository = InventoryRepository()
    var resultStatus: MutableLiveData<ResultStatusInventory>? = MutableLiveData()
    var farmDevice: MutableLiveData<BaseResultItem<FarmDevice>?>? = MutableLiveData()
    var thuoctinh: MutableLiveData<ResultThuocTinhDong>? = MutableLiveData()
    var statusConfirm: MutableLiveData<BaseResultItem<ResultTask>?>? =
        MutableLiveData()
    var deviceConfirm: MutableLiveData<BaseResultItem<ItemConfirmInventory>>? =
        MutableLiveData()
    var detailItemConfirm: MutableLiveData<ResultDetailBuy>? = MutableLiveData()
    var deviceSearch: MutableLiveData<BaseResultItem<FarmDevice>>? =
        MutableLiveData()

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
        }, {
            errorMessage?.value = it
        })
    }

    fun lichMuaTheoNgay(xacNhan: String) {
        inventoryRepository.lichMuaHangTheoNgay(xacNhan, {
            deviceConfirm?.value = it
        }, {
            errorMessage?.value = it
        })

    }

    fun chiTietMuaHang(id: Int) {
        inventoryRepository.chiTietMuaHang(id, {
            detailItemConfirm?.value = it
        }, {
            errorMessage?.value = it
        })
    }

    fun searchItem(filter: String, pageIndex: Int) {
        inventoryRepository.searchItem(filter, pageIndex, {
            deviceSearch?.value = it
        }, {
            errorMessage?.value = it
        })
    }
}