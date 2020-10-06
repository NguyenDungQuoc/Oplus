package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.base.BaseResponse
import com.example.oplus.model.ResultGetMenu
import com.example.oplus.repository.MenuRepository

class MenuViewModel : ViewModel() {
    var menuRepository: MenuRepository = MenuRepository()
    var resultMenu: MutableLiveData<BaseResponse<ResultGetMenu>>? = MutableLiveData()
    var errorMessage: MutableLiveData<String>? = MutableLiveData()

    fun getMenu() {
        menuRepository.getMenu({
            resultMenu?.value = it
        }, {
            errorMessage?.value = it
        })
    }
}