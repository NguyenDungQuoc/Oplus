package com.example.oplus.repository

import com.example.oplus.`interface`.FailureInterface
import com.example.oplus.common.Common
import com.example.oplus.model.ResultBaseDetail
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO

class FailureRepository : BaseRepository() {
    private val failureService: FailureInterface =
            Common.retrofitService.create(FailureInterface::class.java)

    fun soLuongCongViec(
        callback: (BaseResultItem<ResultTask>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(failureService.soLuongCongViec(), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)

        })
    }


    fun congViecTheoNgay(
        tabName: String, ngay: String,
        callback: (BaseResultItem<ResultDayWork>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        handleResponse(failureService.congViecTheoNgay(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }

    fun congViecTheoNgay1(
        tabName: String, ngay: String,
        callback: (BaseResultItem<ResultBacklogDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        handleResponse(failureService.congViecTheoNgay1(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }

    fun congViecTheoThang(
        rq: TaskRequestDTO,
        callback: (BaseResultItem<ResultDayWork>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(failureService.congViecTheoThang(rq = rq),callback,callbackError)


    }

    fun chiTietCongViec(
        iD: Int,
        callback: (ResultBaseDetail?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val id = XacNhanRequestDTO()
        id.iD = iD
        handleResponse(failureService.chiTietCongViec(ID = id),callback,callbackError)


    }

    fun laySuCoCuaThietBi(
        iD: Int,
        callback: (ResultBaseDetail?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val id = XacNhanRequestDTO()
        id.iD = iD
        handleResponse( failureService.laySuCoCuaThietBi(ID = id),callback,callbackError)

    }
}