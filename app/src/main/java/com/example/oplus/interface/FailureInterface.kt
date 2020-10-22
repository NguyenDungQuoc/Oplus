package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FailureInterface {
    @POST("api/suco/SoLuongCongViec")
    fun soLuongCongViec(): Call<BaseResponse<BaseResultItem<ResultTask>>>
    @POST("api/suco/CongViecTheoNgay")
    fun congViecTheoNgay(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
}