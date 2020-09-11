package com.example.oplus.model

data class ResultGetMenu(
    var Items: MutableList<ItemResultMenu>? = null,
    var TotalTask: Int? = null,
    var TotalNotification: Int? = null,
    var IsQuanLyLich: Boolean = false
)