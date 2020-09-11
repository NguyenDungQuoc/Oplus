package com.example.oplus.common

import com.example.oplus.retrofitclient.RetrofitClient
import retrofit2.Retrofit

object Common {
    private const val BASE_URL = "http://apioplus.itpsolution.net"
    val retrofitService: Retrofit
    get() = RetrofitClient.getClient(BASE_URL)
}