package com.example.oplus.repository

import com.example.oplus.`interface`.SearchInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.inventory.Properties
import com.example.oplus.model.search.BaseSearchRequestDTO
import com.example.oplus.model.search.ResultSearchDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {
    private val searchService: SearchInterface =
        Common.retrofitService.create(SearchInterface::class.java)

    fun layDanhSachTrangThai(
        callback: (BaseResponse<BaseResultItem<Properties>>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        searchService.layDanhSachTrangThai().enqueue(object :
            Callback<BaseResponse<BaseResultItem<Properties>>> {
            override fun onResponse(
                call: Call<BaseResponse<BaseResultItem<Properties>>>,
                response: Response<BaseResponse<BaseResultItem<Properties>>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(
                call: Call<BaseResponse<BaseResultItem<Properties>>>,
                t: Throwable
            ) {
                callbackError.invoke(("Error"))
            }

        })
    }

    fun timcongviec(
        rq : BaseSearchRequestDTO,
        callback: (BaseResponse<ResultSearchDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        searchService.timcongviec(rq = rq).enqueue(object :Callback<BaseResponse<ResultSearchDTO>>{
            override fun onResponse(
                call: Call<BaseResponse<ResultSearchDTO>>,
                response: Response<BaseResponse<ResultSearchDTO>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(call: Call<BaseResponse<ResultSearchDTO>>, t: Throwable) {
                callbackError.invoke("Error")
            }

        })
    }
}