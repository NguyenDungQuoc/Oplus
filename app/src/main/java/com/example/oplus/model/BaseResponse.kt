package com.example.oplus.model

class BaseResponse<T>(
    var Title:String? = null,
    var Result:T? = null,
    var Status:StatusLogin? = null
)