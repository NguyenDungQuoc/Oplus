package com.example.oplus.retrofitclient

import com.example.oplus.model.base.Base
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseURL: String): Retrofit {
        if (retrofit == null) {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                        val newBuilder = request.newBuilder()
                            .header("Token", Base.loginData?.Token ?: "")
                            .header("SiteUrl", Base.loginData?.SiteUrl ?: "")
                            .header("Lang", "1066")
                            .method(request.method(), request.body())
                            .build()
                        return chain.proceed(newBuilder)
                    }
                })
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build()
        }
        return retrofit!!
    }

}