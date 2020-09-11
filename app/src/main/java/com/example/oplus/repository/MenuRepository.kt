package com.example.oplus.repository

import com.example.oplus.`interface`.MenuInterface
import com.example.oplus.common.Common
import com.example.oplus.model.BaseResponse
import com.example.oplus.model.ResultGetMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuRepository {
    private val menuRepository: MenuInterface =
        Common.retrofitService.create(MenuInterface::class.java)
    fun getMenu(
        callback: (BaseResponse<ResultGetMenu>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        menuRepository.getMenu().enqueue(object : Callback<BaseResponse<ResultGetMenu>>{
            override fun onResponse(
                call: Call<BaseResponse<ResultGetMenu>>,
                response: Response<BaseResponse<ResultGetMenu>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<BaseResponse<ResultGetMenu>>, t: Throwable) {
                callbackError.invoke("Error")
            }

        })
    }
}