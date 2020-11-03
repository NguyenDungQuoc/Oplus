package com.example.oplus.`interface`

import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FailureInterface {
    @POST("api/suco/SoLuongCongViec")
    fun soLuongCongViec(): Call<BaseResponse<BaseResultItem<ResultTask>>>
    @POST("api/suco/CongViecTheoNgay")
    fun congViecTheoNgay(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
    @POST("api/suco/CongViecTheoNgay")
    fun congViecTheoNgay1(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultBacklogDTO>>>
    @POST("api/suco/CongViecTheoThang")
    fun congViecTheoThang(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
    @POST("api/suco/ChiTietCongViec")
    fun chiTietCongViec(@Body ID: XacNhanRequestDTO): Call<BaseResponse<ResultBaseDetail>>
    @POST("api/suco/LaySuCoCuaThietBi")
    fun laySuCoCuaThietBi(@Body ID: XacNhanRequestDTO): Call<BaseResponse<ResultBaseDetail>>
}