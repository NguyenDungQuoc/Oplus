package com.example.oplus.model

data class ResultThuocTinhDong(
    var listName: String? = null,
    var itemId: Int? = null,
    var title: String? = null,
    var soLuongChoNhap: Float? = null,
    var hinh: MutableList<String>? = null,
    var thuocTinh: MutableList<Properties>? = null

)