package com.example.oplus.`interface`

import com.example.oplus.model.BaseResponse
import com.example.oplus.model.ResultGetMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface MenuInterface {
    @POST("api/admin/GetMenu")
    fun getMenu(): Call<BaseResponse<ResultGetMenu>>
}