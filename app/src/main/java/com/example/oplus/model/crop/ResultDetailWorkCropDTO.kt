package com.example.oplus.model.crop

import com.example.oplus.model.failure.MediaDTO
import com.example.oplus.model.general.StatusValue
import com.example.oplus.model.inventory.ButtonDetailBuy

data class ResultDetailWorkCropDTO(
    var iD: Int? = null,
    var webUrl:String? =null,
    var title: StatusValue? = null,
    var dinhKemQuyTrinhUrl:String? =null,
    var iDLoTrong: Int? = null,
    var status:Int? =null,
    var loaiCongViec:StatusValue? =null,
    var items:MutableList<StatusValue>? =null,
    var labelSoRoDungLai:String? =null,
    var soRoTrenLo:String? =null,
    var soRoMax: Int? = null,
    var soRo: Int? = null,
    var soRoDungLaiSauXuLy: Int? = null,
    var labelThietBiGiamSat:String? =null,
    var thietBiGiamSat:MutableList<VatTuDTO>? =null,
    var labelThuHoach:String? =null,
    var labelHinh:String? =null,
    var media: MutableList<MediaDTO>? = null,
    var buttons: MutableList<ButtonDetailBuy>? = null,
    var nguoiXuLy:StatusValue? =null,
    var thoiGianBatDau: StatusValue? = null,
    var thoiGianKetThuc: StatusValue? = null,
    var danhGia:StatusValue? =null,
    var isNguoiXuLy:Boolean = false,
    var isNguoiDanhGia:Boolean = false,
    var isNguoiTao:Boolean = false
)
