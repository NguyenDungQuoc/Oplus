package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AdoptFishInterface {
    @POST("api/nuoica/GetSoLuongLichLamViec")
    fun soLuongCongViec(): Call<BaseResponse<BaseResultItem<ResultTask>>>
    @POST("api/nuoica/GetLichLamViec")
    fun congViecTheoNgay(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
    @POST("api/nuoica/GetLichLamViec")
    fun congViecTheoNgay1(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultBacklogDTO>>>
}