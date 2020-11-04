package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.giamsat.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GiamSatInterface {
    @POST("api/giamsat/GetListHe")
    fun getListHe() : Call<BaseResponse<BaseResultItem<GiamSatItem>>>

    @POST("api/giamsat/GetListPhanKhu")
    fun getListPhanKhu(@Body rq: ItemRequestDTO): Call<BaseResponse<BaseResultItem<GiamSatItem>>>

    @POST("api/giamsat/ThongTinThietBi")
    fun thongTinThietBi(): Call<BaseResponse<BaseResultItem<InfoItem>>>

    @POST("api/giamsat/GetListThietBi")
    fun getListThietBi(@Body rq: AreaRequestDTO): Call<BaseResponse<BaseResultItem<ResultItemForArea>>>

    @POST("api/giamsat/CongViecTheoThietBi")
    fun congViecTheoThietBi(@Body rq: AreaRequestDTO): Call<BaseResponse<BaseResultItem<ResultDayWork>>>
}