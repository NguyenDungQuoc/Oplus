package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.ResultLogin

import com.example.oplus.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private var loginRepository: LoginRepository = LoginRepository()
    var result: MutableLiveData<ResultLogin> = MutableLiveData()
    var tilte:MutableLiveData<String> = MutableLiveData()
    var errorMessage:MutableLiveData<String>? = MutableLiveData()

    fun login(userName: String, password: String) {
        loginRepository.login(userName, password, callback =  {
           if(it?.Status?.Code == 200){
               result.value = it.Result
           }else{
                errorMessage?.value = it?.Status?.Desc
           }
            tilte.value = it?.Title
        }, callbackError = {
            errorMessage?.value = it
        })
    }
}