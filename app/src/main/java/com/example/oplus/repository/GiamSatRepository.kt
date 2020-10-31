package com.example.oplus.repository

import com.example.oplus.`interface`.GiamSatInterface
import com.example.oplus.common.Common
import com.example.oplus.model.base.BaseResultItem
import com.example.oplus.model.giamsat.*

class GiamSatRepository : BaseRepository() {
    private val giamSatService: GiamSatInterface =
        Common.retrofitService.create(GiamSatInterface::class.java)

    fun getListHe(
        callback: (BaseResultItem<GiamSatItem>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(giamSatService.getListHe(), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })
    }

    fun getListPhanKhu(
        iDHe: Int,
        callback: (BaseResultItem<GiamSatItem>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = ItemRequestDTO()
        rq.iDHe = iDHe
        handleResponse( giamSatService.getListPhanKhu(rq = rq),{
            callback.invoke(it)
        },{
            callbackError.invoke(it)
        })

    }

    fun thongTinThietBi(
        callback: (BaseResultItem<InfoItem>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        handleResponse(giamSatService.thongTinThietBi(),{
            callback.invoke(it)
        },{
            callbackError.invoke(it)
        })

    }

    fun getListThietBi(
        phanKhu: String,
        callback: (BaseResultItem<ResultItemForArea>?) -> (Unit),
        callbackError: (String?) -> (Unit)
    ) {
        val rq = AreaRequestDTO()
        rq.phanKhu = phanKhu
        handleResponse(giamSatService.getListThietBi(rq = rq), {
            callback.invoke(it)
        }, {
            callbackError.invoke(it)
        })

    }
}