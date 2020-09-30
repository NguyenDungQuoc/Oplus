package com.example.oplus.model.base

data class BaseResultItem<T>(
    var items: MutableList<T>? = null
)