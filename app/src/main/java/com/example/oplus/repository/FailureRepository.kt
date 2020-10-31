package com.example.oplus.repository

import com.example.oplus.`interface`.FailureInterface
import com.example.oplus.common.Common
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FailureRepository : BaseRepository() {
    private val failureService: FailureInterface =
        Common.retrofitService.create(FailureInterface::class.java)

    fun soLuongCongViec(
        callback: (BaseResultItem<ResultTask>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(failureService.soLuongCongViec(), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)

        })
    }


    fun congViecTheoNgay(
        tabName: String, ngay: String,
        callback: (BaseResultItem<ResultDayWork>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        handleResponse(failureService.congViecTheoNgay(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }

    fun congViecTheoNgay1(
        tabName: String, ngay: String,
        callback: (BaseResultItem<ResultBacklogDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        handleResponse(  failureService.congViecTheoNgay1(rq = rq),{
            callback.invoke(it)
        },{
            callbackError.invoke(it)
        })

    }

    fun congViecTheoThang(
        rq: TaskRequestDTO,
        callback: (BaseResponse<BaseResultItem<ResultDayWork>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        failureService.congViecTheoThang(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultDayWork>>> {
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<ResultDayWork>>>,
                    response: Response<BaseResponse<BaseResultItem<ResultDayWork>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<ResultDayWork>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun chiTietCongViec(
        iD: Int,
        callback: (BaseResponse<ResultBaseDetail>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val id = XacNhanRequestDTO()
        id.iD = iD
        failureService.chiTietCongViec(ID = id)
            .enqueue(object : Callback<BaseResponse<ResultBaseDetail>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResultBaseDetail>>,
                    response: Response<BaseResponse<ResultBaseDetail>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(call: Call<BaseResponse<ResultBaseDetail>>, t: Throwable) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun laySuCoCuaThietBi(
        iD: Int,
        callback: (BaseResponse<ResultBaseDetail>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val id = XacNhanRequestDTO()
        id.iD = iD
        failureService.laySuCoCuaThietBi(ID = id)
            .enqueue(object : Callback<BaseResponse<ResultBaseDetail>> {
                override fun onResponse(
                    call: Call<BaseResponse<ResultBaseDetail>>,
                    response: Response<BaseResponse<ResultBaseDetail>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(call: Call<BaseResponse<ResultBaseDetail>>, t: Throwable) {
                    callbackError.invoke("Error")
                }

            })
    }
}