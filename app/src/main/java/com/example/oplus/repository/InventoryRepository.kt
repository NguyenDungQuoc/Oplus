package com.example.oplus.repository

import com.example.oplus.`interface`.InventoryInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.*
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
        callback: (BaseResponse<BaseResultItem<FarmDevice>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val danhSach = DanhSachTonKhoRequestDTO()
        danhSach.isEnd = IsEnd
        danhSach.pageIndex = PageIndex
        inventoryRepository.getDanhSachTonKho(danhSachTonKho = danhSach)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<FarmDevice>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<FarmDevice>>>,
                    response: Response<BaseResponse<BaseResultItem<FarmDevice>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<FarmDevice>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }

    fun getThuocTinhDong(
        ListName: String, ItemId: Int,
        callback: (BaseResponse<ResultThuocTinhDong>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val thuocTinh = ThuocTinhDongRequestDTO()
        thuocTinh.ListName = ListName
        thuocTinh.ItemId = ItemId

        inventoryRepository.getThuocTinhDong(thuocTinhDong = thuocTinh)
            .enqueue(object : Callback<BaseResponse<ResultThuocTinhDong>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResultThuocTinhDong>>,
                    response: Response<BaseResponse<ResultThuocTinhDong>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<ResultThuocTinhDong>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun demLichMuaHang(
        ID: Int, XacNhan: Boolean,
        callback: (BaseResponse<BaseResultItem<StatusConfirmInventory>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val xacNhan = XacNhanRequestDTO()
        xacNhan.ID = ID
        xacNhan.XacNhan = XacNhan
        inventoryRepository.demLichMuaHang(xacNhan = xacNhan)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<StatusConfirmInventory>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<StatusConfirmInventory>>>,
                    response: Response<BaseResponse<BaseResultItem<StatusConfirmInventory>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<StatusConfirmInventory>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }


            })
    }

    fun lichMuaHangTheoNgay(
        xacNhan: String,
        callback: (BaseResponse<BaseResultItem<ItemConfirmInventory>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val request = DetailConfirmRequestDTO()
        request.xacNhan = xacNhan
        inventoryRepository.lichMuaHangTheoNgay(request = request)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ItemConfirmInventory>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<ItemConfirmInventory>>>,
                    response: Response<BaseResponse<BaseResultItem<ItemConfirmInventory>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<ItemConfirmInventory>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun chiTietMuaHang(
        ID: Int,
        callback: (BaseResponse<ResultDetailBuy>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        val rq = XacNhanRequestDTO()
        rq.ID = ID
        inventoryRepository.chiTietMuaHang(rq = rq).enqueue(object : Callback<BaseResponse<ResultDetailBuy>>{
            override fun onResponse(
                call: Call<BaseResponse<ResultDetailBuy>>,
                response: Response<BaseResponse<ResultDetailBuy>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<BaseResponse<ResultDetailBuy>>, t: Throwable) {
                callbackError.invoke("Error")
            }

        })
    }

    fun searchItem(
        filter:String,pageIndex:Int,
        callback: (BaseResponse<BaseResultItem<FarmDevice>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        val rq = SearchRequestDTO()
        rq.filter = filter
        rq.pageIndex = pageIndex
        inventoryRepository.searchItem(rq = rq).enqueue(object : Callback<BaseResponse<BaseResultItem<FarmDevice>>>{
            override fun onResponse(
                call: Call<BaseResponse<BaseResultItem<FarmDevice>>>,
                response: Response<BaseResponse<BaseResultItem<FarmDevice>>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(
                call: Call<BaseResponse<BaseResultItem<FarmDevice>>>,
                t: Throwable
            ) {
                callbackError.invoke("Error")
            }

        })
    }
}