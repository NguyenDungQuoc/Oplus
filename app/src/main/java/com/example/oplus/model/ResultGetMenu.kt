package com.example.oplus.model

data class ResultGetMenu(
    var items: MutableList<ItemResultMenu>? = null,
    var totalTask: Int? = null,
    var totalNotification: Int? = null,
    var isQuanLyLich: Boolean = false
)