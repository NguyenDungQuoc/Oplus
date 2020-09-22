package com.example.oplus.model

class BaseResponse<T>(
    var title:String? = null,
    var result:T? = null,
    var status:StatusLogin? = null
)