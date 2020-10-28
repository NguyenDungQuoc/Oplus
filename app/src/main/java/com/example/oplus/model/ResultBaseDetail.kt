package com.example.oplus.model

import com.example.oplus.model.failure.MediaDTO
import com.example.oplus.model.failure.UserHandleDTO
import com.example.oplus.model.general.StatusValue
import com.example.oplus.model.inventory.ButtonDetailBuy

class ResultBaseDetail {
    var thoiGianBao: StatusValue? = null
    var batDauThucTe: StatusValue? = null
    var ketThucThucTe: StatusValue? = null
    var batDau: StatusValue? = null
    var ketThuc: StatusValue? = null
    var trangThai: StatusValue? = null
    var tinhTrang: StatusValue? = null
    var lstTinhTrang: String? = null
    var nguoiXuLy: UserHandleDTO? = null
    var lstNguoiXuLy: String? = null
    var danhGia: StatusValue? = null
    var lstDanhGia: StatusValue? = null
    var tieuDe: StatusValue? = null
    var isNguoiXuLy: Boolean? = null
    var labelHinh: String? = null
    var media: MutableList<MediaDTO>? = null
    var buttons: MutableList<ButtonDetailBuy>? = null
    var status: Int? = null
    var lstThietBiGiamSat: String? = null
    var thietBiGiamSat: StatusValue? = null
}