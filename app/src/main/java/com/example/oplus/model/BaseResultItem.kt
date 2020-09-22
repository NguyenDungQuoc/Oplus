package com.example.oplus.model

data class BaseResultItem<T>(
    var items: MutableList<T>? = null
)