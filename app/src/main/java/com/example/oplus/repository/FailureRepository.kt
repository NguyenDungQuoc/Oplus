package com.example.oplus.repository

import com.example.oplus.`interface`.FailureInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
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
}