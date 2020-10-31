package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.inventory.ResultTask

open class BaseTaskViewModel:BaseViewModel() {
    var task: MutableLiveData<MutableList<ResultTask>?> = MutableLiveData()
    var workDay:MutableLiveData<MutableList<ResultDayWork>> = MutableLiveData()
    var backlog:MutableLiveData<MutableList<ResultBacklogDTO>> = MutableLiveData()
    var item:MutableLiveData<ResultBaseDetail> = MutableLiveData()
}