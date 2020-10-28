package com.example.oplus.`interface`

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.Properties
import com.example.oplus.model.search.BaseSearchRequestDTO
import com.example.oplus.model.search.ResultSearchDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface SearchInterface {
    @POST("api/caytrong/LayDanhSachTrangThai")
    fun layDanhSachTrangThai(): Call<BaseResponse<BaseResultItem<Properties>>>
    @POST("api/custom/timcongviec")
    fun timcongviec(@Body rq: BaseSearchRequestDTO) : Call<BaseResponse<ResultSearchDTO>>
}