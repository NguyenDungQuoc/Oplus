package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oplus.model.CurrentUserProfile
import com.example.oplus.model.login.ResultLogin

import com.example.oplus.repository.LoginRepository

class LoginViewModel : ViewModel() {
    private var loginRepository: LoginRepository = LoginRepository()
    var result: MutableLiveData<ResultLogin> = MutableLiveData()
    var currentUserProfile: MutableLiveData<CurrentUserProfile> = MutableLiveData()
    var tilte: MutableLiveData<String> = MutableLiveData()
    var newResult: MutableLiveData<ResultLogin> = MutableLiveData()
    var errorMessage: MutableLiveData<String>? = MutableLiveData()

    fun login(userName: String, password: String) {
        loginRepository.login(userName, password, callback = {
            if (it?.status?.code == 200) {
                result.value = it.result
            } else {
                errorMessage?.value = it?.status?.desc
            }
            tilte.value = it?.title
        }, callbackError = {
            errorMessage?.value = it
        })
    }

    fun getCurrentUserProfile() {
        loginRepository.getCurrentUserProfile({
            currentUserProfile?.value = it?.result
        }, {
            errorMessage?.value = it
        })
    }

    fun doiMatKhau(password: String, newPassword: String) {
        loginRepository.doiMatKhau(password, newPassword, callback = {
            if (it?.status?.code == 200) {
                newResult.value = it.result
            } else {
                errorMessage?.value = it?.status?.desc
            }
            tilte.value = it?.title
        }, callbackError = {
            errorMessage?.value = it
        })
    }
}