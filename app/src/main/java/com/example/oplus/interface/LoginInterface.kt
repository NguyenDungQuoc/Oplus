package com.example.oplus.`interface`

import com.example.oplus.model.login.LoginModel
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.CurrentUserProfile
import com.example.oplus.model.login.NewPassWordReQuestDTO
import com.example.oplus.model.login.ResultLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginInterface {
//    @GET("")
//    fun getData(): Call<>
    @POST("api/account/Login")
    fun login(@Body rq: LoginModel): Call<BaseResponse<ResultLogin>>
    @POST("api/account/GetCurrentUserProfile")
    fun getCurrentUserProfile() : Call<BaseResponse<CurrentUserProfile>>
    @POST("api/account/DoiMatKhau")
    fun doiMatKhau(@Body rq: NewPassWordReQuestDTO) : Call<BaseResponse<ResultLogin>>
}