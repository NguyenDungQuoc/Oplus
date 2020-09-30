package com.example.oplus.model.inventory

data class ResultDetailBuy(
    var listName: String? = null,
    var itemId: Int? = null,
    var title: String? = null,
    var hinh: MutableList<String>? = null,
    var thuocTinh: MutableList<Properties>? = null,
    var taiLieu: String? = null,
    var button: MutableList<ButtonDetailBuy>? = null,

    )