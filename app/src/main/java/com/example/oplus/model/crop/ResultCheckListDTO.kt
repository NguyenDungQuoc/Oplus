package com.example.oplus.model.crop

import com.example.oplus.model.general.StatusValue
import com.example.oplus.model.inventory.ButtonDetailBuy

class ResultCheckListDTO(
    var titleForm: String? = null,
    var header: MutableList<String>? = null,
    var items: MutableList<ItemCheckList>? = null,
    var itemsBaoCao: MutableList<String>? = null,
    var buttons: MutableList<ButtonDetailBuy>? = null,
    var message: String? = null
)

class ItemCheckList(
    var sTT: Int? = null,
    var title: String? = null,
    var icon: String? = null,
    var check: Boolean = false,
    var ma: StatusValue? = null,
    var soLuongTon: StatusValue? = null,
    var dinhLuong: String? = null,
    var listVatTu: MutableList<VatTuDTO>? = null
)

class VatTuDTO(
    var iD: Int? = null,
    var icon: String? = null,
    var title: String? = null,
    var roperties: MutableList<StatusValue>? = null
)
