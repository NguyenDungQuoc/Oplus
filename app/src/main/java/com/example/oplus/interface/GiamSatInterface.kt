package com.example.oplus.`interface`

import com.example.oplus.extensions.CallCustom
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.giamsat.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GiamSatInterface {
    @POST("api/giamsat/GetListHe")
    fun getListHe() : CallCustom<BaseResultItem<GiamSatItem>>

    @POST("api/giamsat/GetListPhanKhu")
    fun getListPhanKhu(@Body rq: ItemRequestDTO): Call<BaseResponse<BaseResultItem<GiamSatItem>>>

    @POST("api/giamsat/ThongTinThietBi")
    fun thongTinThietBi(): Call<BaseResponse<BaseResultItem<InfoItem>>>

    @POST("api/giamsat/GetListThietBi")
    fun getListThietBi(@Body rq: AreaRequestDTO): CallCustom<BaseResultItem<ResultItemForArea>>
}