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
import java.util.*

class FailureRepository {
    private val failureService: FailureInterface =
        Common.retrofitService.create(FailureInterface::class.java)

    fun soLuongCongViec(
        callback: (BaseResponse<BaseResultItem<ResultTask>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        failureService.soLuongCongViec()
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultTask>>>{
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<ResultTask>>>,
                    response: Response<BaseResponse<BaseResultItem<ResultTask>>>
                ) {
                   callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<ResultTask>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }
            })
    }
    fun congViecTheoNgay(
        tabName:String,ngay:String,
        callback: (BaseResponse<BaseResultItem<ResultDayWork>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        failureService.congViecTheoNgay(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultDayWork>>>{
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

    fun congViecTheoNgay1(
        tabName:String, ngay:String,
        callback: (BaseResponse<BaseResultItem<ResultBacklogDTO>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        failureService.congViecTheoNgay1(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultBacklogDTO>>>{
                override fun onResponse(
                    call: Call<BaseResponse<BaseResultItem<ResultBacklogDTO>>>,
                    response: Response<BaseResponse<BaseResultItem<ResultBacklogDTO>>>
                ) {
                    callback.invoke(response.body())
                }

                override fun onFailure(
                    call: Call<BaseResponse<BaseResultItem<ResultBacklogDTO>>>,
                    t: Throwable
                ) {
                    callbackError.invoke("Error")
                }

            })
    }

    fun congViecTheoThang(
        rq :TaskRequestDTO,
        callback: (BaseResponse<BaseResultItem<ResultDayWork>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        failureService.congViecTheoThang(rq = rq)
            .enqueue(object : Callback<BaseResponse<BaseResultItem<ResultDayWork>>>{
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
    ){
        val id = XacNhanRequestDTO()
        id.iD = iD
        failureService.chiTietCongViec(ID = id).enqueue(object : Callback<BaseResponse<ResultBaseDetail>>{
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