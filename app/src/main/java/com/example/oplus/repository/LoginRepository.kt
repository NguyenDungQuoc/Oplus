package com.example.oplus.repository

import com.example.oplus.`interface`.LoginInterface
import com.example.oplus.common.Common
import com.example.oplus.model.BaseResponse
import com.example.oplus.model.LoginModel
import com.example.oplus.model.ResultLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    private val loginService: LoginInterface =
        Common.retrofitService.create(LoginInterface::class.java)

    fun login(
        userName: String,
        password: String,
        callback: (BaseResponse<ResultLogin>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        var user = LoginModel()
        user.LoginName = userName
        user.Password = password
        loginService.login(rq = user).enqueue(object : Callback<BaseResponse<ResultLogin>> {
            override fun onResponse(
                call: Call<BaseResponse<ResultLogin>>,
                response: Response<BaseResponse<ResultLogin>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<BaseResponse<ResultLogin>>, t: Throwable) {
                callbackError.invoke("Lỗi")
            }
        })
    }
}