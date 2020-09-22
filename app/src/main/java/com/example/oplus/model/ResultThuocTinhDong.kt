package com.example.oplus.model

data class ResultThuocTinhDong(
    var ListName: String? = null,
    var ItemId: Int? = null,
    var Title: String? = null,
    var SoLuongChoNhap: Float? = null,
    var Hinh: MutableList<String>? = null,
    var ThuocTinh: MutableList<Properties>? = null

)