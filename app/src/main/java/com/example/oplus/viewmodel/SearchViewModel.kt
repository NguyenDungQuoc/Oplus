package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData

import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.inventory.Properties
import com.example.oplus.model.search.BaseSearchRequestDTO
import com.example.oplus.model.search.ResultSearchDTO
import com.example.oplus.repository.SearchRepository

class SearchViewModel : BaseViewModel() {
    var searchRepository: SearchRepository = SearchRepository()
    var status: MutableLiveData<MutableList<Properties>> = MutableLiveData()
    var result: MutableLiveData<ResultSearchDTO> = MutableLiveData()

    fun layDanhSachTrangThai() {
        searchRepository.layDanhSachTrangThai({
            status.value = it?.result?.items
        }, {
            errorMessage?.value = it
        })
    }

    fun timcongviec(rq: BaseSearchRequestDTO) {
        searchRepository.timcongviec(rq, {
            result.value = it?.result
        },{
            errorMessage?.value = it
        })
    }
}