package com.example.oplus.repository

import com.example.oplus.model.base.BaseResponse
import com.example.oplus.viewmodel.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository() {

    fun <T> handleResponse(
        response: Call<BaseResponse<T>>,
        onSuccess: (T?) -> Unit,
        onFail: ((String?) -> Unit)?
    ) {
        response.enqueue(object : Callback<BaseResponse<T>> {

            override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
                onFail?.invoke(t.message)
            }

            override fun onResponse(
                call: Call<BaseResponse<T>>,
                response: Response<BaseResponse<T>>
            ) {
                val statusCode = response.body()?.status?.code
                if (statusCode == 200) {
                    onSuccess.invoke(response.body()?.result)
                } else {
                    onFail?.invoke(response.body()?.status?.desc)
                }
            }

        })
    }
}