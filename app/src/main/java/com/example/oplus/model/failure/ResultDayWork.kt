package com.example.oplus.model.failure

import com.example.oplus.model.general.StatusValue

data class ResultDayWork (
    var iD:Int?=null,
    var icon:String? =null,
    var thoiGianBatDau:String? =null,
    var title:String? =null,
    var mauTieuDe:String? =null,
    var mauTrangThai:String? =null,
    var trangThai: StatusValue? =null,
    var ma:StatusValue? =null,
    var status:Int?=null,
)
