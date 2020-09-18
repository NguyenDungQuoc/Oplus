package com.example.oplus.repository

import com.example.oplus.`interface`.InventoryInterface
import com.example.oplus.common.Common
import com.example.oplus.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryRepository {
    private val inventoryRepository: InventoryInterface =
        Common.retrofitService.create(InventoryInterface::class.java)

    fun getSoLuongTonKho(
        callback: (BaseResponse<ResultStatusInventory>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        inventoryRepository.getSoLuongTonKHo()
            .enqueue(object : Callback<BaseResponse<ResultStatusInventory>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResultStatusInventory>>,
                    response: Response<BaseResponse<ResultStatusInventory>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<ResultStatusInventory>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }

    fun getDanhSachTonKho(
        IsEnd: Boolean, PageIndex: Int,
        callback: (BaseResponse<ResultItemInventory>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val danhSach = DanhSachTonKhoRequestDTO()
        danhSach.IsEnd = IsEnd
        danhSach.PageIndex = PageIndex
        inventoryRepository.getDanhSachTonKho(danhSachTonKho = danhSach)
            .enqueue(object : Callback<BaseResponse<ResultItemInventory>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResultItemInventory>>,
                    response: Response<BaseResponse<ResultItemInventory>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<ResultItemInventory>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }

    fun getThuocTinhDong(
        ListName:String,ItemId:Int,
        callback: (BaseResponse<ResultThuocTinhDong>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        val thuoctinh = ThuocTinhDongRequestDTO()
        thuoctinh.ListName = ListName
        thuoctinh.ItemId = ItemId

        inventoryRepository.getThuocTinhDong(thuocTinhDong = thuoctinh).enqueue(object : Callback<BaseResponse<ResultThuocTinhDong>>{
            override fun onResponse(
                call: Call<BaseResponse<ResultThuocTinhDong>>,
                response: Response<BaseResponse<ResultThuocTinhDong>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<BaseResponse<ResultThuocTinhDong>>, t: Throwable) {
                callbackError.invoke("Error")
            }

        })
    }
}