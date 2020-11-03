package com.example.oplus.repository

import com.example.oplus.`interface`.AdoptFishInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask

class AdoptFishRepository : BaseRepository() {
    private val adoptFishService: AdoptFishInterface =
        Common.retrofitService.create(AdoptFishInterface::class.java)

    fun soLuongCongViec(
        callback: (BaseResultItem<ResultTask>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(adoptFishService.soLuongCongViec(), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)

        })
    }

    fun congViecTheoNgay(
        tabName: String, ngay: String,loNuoi:Int,
        callback: (BaseResultItem<ResultDayWork>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        rq.loNuoi = loNuoi
        handleResponse(adoptFishService.congViecTheoNgay(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }
    fun congViecTheoNgay1(
        tabName: String, ngay: String,loNuoi:Int,
        callback: (BaseResultItem<ResultBacklogDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        rq.loNuoi = loNuoi
        handleResponse(adoptFishService.congViecTheoNgay1(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }
}