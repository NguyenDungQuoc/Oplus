package com.example.oplus.extensions

import com.example.oplus.model.base.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallCustom<T> : Call<BaseResponse<T>> {
    fun enqueue(onSuccess: (T?) -> Unit, onFail: (String?) -> Unit) {
        this.enqueue(object : Callback<BaseResponse<T>> {

            override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
                onFail.invoke(t.message)
            }


            override fun onResponse(
                call: Call<BaseResponse<T>>,
                response: Response<BaseResponse<T>>
            ) {
                var statusCode = response.body()?.status?.code
                if (statusCode == 200) {
                    onSuccess.invoke(response.body()?.result)
                } else {
                    onFail.invoke(response.body()?.status?.desc)
                }
            }

        })
    }
}