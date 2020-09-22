package com.example.oplus.`interface`

import com.example.oplus.model.LoginModel
import com.example.oplus.model.BaseResponse
import com.example.oplus.model.CurrentUserProfile
import com.example.oplus.model.ResultLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginInterface {
//    @GET("")
//    fun getData(): Call<>
    @POST("api/account/Login")
    fun login(@Body rq: LoginModel): Call<BaseResponse<ResultLogin>>
    @POST("api/account/GetCurrentUserProfile")
    fun getCurrentUserProfile() : Call<BaseResponse<CurrentUserProfile>>
}