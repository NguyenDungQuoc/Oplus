package com.example.oplus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.crop.ClusterDTO
import com.example.oplus.model.crop.PropertiesBugDTO
import com.example.oplus.model.crop.ResultDetailWorkCropDTO
import com.example.oplus.model.crop.VatTuDTO
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.inventory.ResultTask

open class BaseTaskViewModel:BaseViewModel() {
    var task: MutableLiveData<MutableList<ResultTask>?> = MutableLiveData()
    var workDay:MutableLiveData<MutableList<ResultDayWork>?>? = MutableLiveData()
    var backlog:MutableLiveData<MutableList<ResultBacklogDTO>?>? = MutableLiveData()
    var item:MutableLiveData<ResultBaseDetail?> = MutableLiveData()
    var cluster:MutableLiveData<MutableList<ClusterDTO>?>? = MutableLiveData()
    var detailWork:MutableLiveData<ResultDetailWorkCropDTO?>? = MutableLiveData()
    var bugs:MutableLiveData<BaseResultItem<VatTuDTO>?>? = MutableLiveData()
    var propertiesBug:MutableLiveData<PropertiesBugDTO?>? = MutableLiveData()
}