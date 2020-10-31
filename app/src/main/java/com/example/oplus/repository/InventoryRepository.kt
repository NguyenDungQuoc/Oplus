package com.example.oplus.repository

import com.example.oplus.`interface`.InventoryInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.*
import com.example.oplus.viewmodel.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryRepository() : BaseRepository() {
    private val inventoryService: InventoryInterface =
        Common.retrofitService.create(InventoryInterface::class.java)

    fun getSoLuongTonKho(
        callback: (ResultStatusInventory?) -> (Unit),
        callbackError: ((String?) -> (Unit))?
    ) {
        handleResponse(inventoryService.getSoLuongTonKHo(), callback,callbackError)
    }

    fun getDanhSachTonKho(
        IsEnd: Boolean, PageIndex: Int,
        callback: (BaseResultItem<FarmDevice>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val danhSach = DanhSachTonKhoRequestDTO()
        danhSach.isEnd = IsEnd
        danhSach.pageIndex = PageIndex
        handleResponse(inventoryService.getDanhSachTonKho(danhSachTonKho = danhSach), callback,callbackError)

    }

    fun getThuocTinhDong(
        ListName: String, ItemId: Int,
        callback: (ResultThuocTinhDong?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val thuocTinh = ThuocTinhDongRequestDTO()
        thuocTinh.ListName = ListName
        thuocTinh.ItemId = ItemId
        handleResponse(inventoryService.getThuocTinhDong(thuocTinhDong = thuocTinh), callback,callbackError)

    }

    fun demLichMuaHang(
        ID: Int, XacNhan: Boolean,
        callback: (BaseResultItem<ResultTask>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val xacNhan = XacNhanRequestDTO()
        xacNhan.iD = ID
        xacNhan.xacNhan = XacNhan
        handleResponse(inventoryService.demLichMuaHang(xacNhan = xacNhan),callback,callbackError)

    }

    fun lichMuaHangTheoNgay(
        xacNhan: String,
        callback: (BaseResultItem<ItemConfirmInventory>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val request = DetailConfirmRequestDTO()
        request.xacNhan = xacNhan
        handleResponse(inventoryService.lichMuaHangTheoNgay(request = request), callback,callbackError)

    }

    fun chiTietMuaHang(
        ID: Int,
        callback: (ResultDetailBuy?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = XacNhanRequestDTO()
        rq.iD = ID
        handleResponse(inventoryService.chiTietMuaHang(rq = rq),callback,callbackError)

    }

    fun searchItem(
        filter: String, pageIndex: Int,
        callback: (BaseResultItem<FarmDevice>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = SearchRequestDTO()
        rq.filter = filter
        rq.pageIndex = pageIndex
        handleResponse(inventoryService.searchItem(rq = rq),callback,callbackError)
    }
}