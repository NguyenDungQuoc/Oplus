package com.example.oplus.repository

import com.example.oplus.`interface`.CropsInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.crop.ClusterDTO
import com.example.oplus.model.crop.ResultCheckListDTO
import com.example.oplus.model.failure.ResultBacklogDTO
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.model.inventory.XacNhanRequestDTO

class CropsRepository : BaseRepository() {
    private val cropsService: CropsInterface =
        Common.retrofitService.create(CropsInterface::class.java)

    fun soLuongCongViec(
        callback: (BaseResultItem<ResultTask>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(cropsService.soLuongCongViec(), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)

        })
    }
    fun congViecTheoNgay(
        tabName: String, ngay: String,loTrong:Int,
        callback: (BaseResultItem<ResultDayWork>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        rq.loTrong = loTrong
        handleResponse(cropsService.congViecTheoNgay(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })
    }
    fun congViecTheoNgay1(
        tabName: String, ngay: String,loTrong:Int,
        callback: (BaseResultItem<ResultBacklogDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = TaskRequestDTO()
        rq.tabName = tabName
        rq.ngay = ngay
        rq.loTrong = loTrong
        handleResponse(cropsService.congViecTheoNgay1(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }
    fun danhSachCheckList(
        iD:Int,
        callback: (ResultCheckListDTO?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        val rq = XacNhanRequestDTO()
        rq.iD = iD
        handleResponse(cropsService.danhSachCheckList(rq = rq),callback,callbackError)
    }

    fun getDanhSachCumLo(
        callback: (BaseResultItem<ClusterDTO>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ){
        handleResponse(cropsService.getDanhSachCumLo(),callback,callbackError)
    }
}