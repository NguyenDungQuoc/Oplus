package com.example.oplus.repository

import com.example.oplus.`interface`.GiamSatInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.giamsat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiamSatRepository {
    private val giamSatService: GiamSatInterface =
        Common.retrofitService.create(GiamSatInterface::class.java)

    fun getListHe(
        callback: (BaseResponse<BaseResultItem<GiamSatItem>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        giamSatService.getListHe()
            .enqueue(object : Callback<BaseResponse<BaseResultItem<GiamSatItem>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<GiamSatItem>>>,
                    response: Response<BaseResponse<BaseResultItem<GiamSatItem>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<GiamSatItem>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }

    fun getListPhanKhu(
        iDHe: Int,
        callback: (BaseResponse<BaseResultItem<GiamSatItem>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = ItemRequestDTO()
        rq.iDHe = iDHe
        giamSatService.getListPhanKhu(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<GiamSatItem>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<GiamSatItem>>>,
                    response: Response<BaseResponse<BaseResultItem<GiamSatItem>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<GiamSatItem>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun thongTinThietBi(
        callback: (BaseResponse<BaseResultItem<InfoItem>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        giamSatService.thongTinThietBi()
            .enqueue(object : Callback<BaseResponse<BaseResultItem<InfoItem>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<InfoItem>>>,
                    response: Response<BaseResponse<BaseResultItem<InfoItem>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<InfoItem>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }

    fun getListThietBi(
        phanKhu: String,
        callback: (BaseResponse<BaseResultItem<ResultItemForArea>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = AreaRequestDTO()
        rq.phanKhu = phanKhu
        giamSatService.getListThietBi(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultItemForArea>>>{
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<ResultItemForArea>>>,
                    response: Response<BaseResponse<BaseResultItem<ResultItemForArea>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<ResultItemForArea>>>,
                    t: Throwable
                ) {
                   callbackError.invoke("Error")
                }

            })
    }
}