package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.crop.ClusterDTO
import com.example.oplus.model.crop.ResultCheckListDTO
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CropsInterface {
    @POST("api/caytrong/GetSoLuongLichLamViec")
    fun soLuongCongViec(): Call<BaseResponse<BaseResultItem<ResultTask>>>
    @POST("api/caytrong/GetLichLamViec")
    fun congViecTheoNgay(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
    @POST("api/caytrong/GetLichLamViec")
    fun congViecTheoNgay1(@Body rq: TaskRequestDTO): Call<BaseResponse<BaseResultItem<ResultBacklogDTO>>>
    @POST("api/caytrong/DanhSachCheckList")
    fun danhSachCheckList(@Body rq: XacNhanRequestDTO): Call<BaseResponse<ResultCheckListDTO>>
    @POST("api/caytrong/GetDanhSachCumLo")
    fun getDanhSachCumLo(): Call<BaseResponse<BaseResultItem<ClusterDTO>>>
}