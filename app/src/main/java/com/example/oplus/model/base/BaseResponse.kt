package com.example.oplus.model.base

import com.example.oplus.model.login.StatusLogin

class BaseResponse<T>(
    var title:String? = null,
    var result:T? = null,
    var status: StatusLogin? = null
)