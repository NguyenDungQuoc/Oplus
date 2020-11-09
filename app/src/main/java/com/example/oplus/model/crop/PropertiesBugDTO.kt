package com.example.oplus.model.crop

import com.example.oplus.model.failure.MediaDTO
import com.example.oplus.model.general.StatusValue
import com.example.oplus.model.inventory.ButtonDetailBuy

class PropertiesBugDTO(
    var iD: Int? = null,
    var title: StatusValue? = null,
    var nhomLoai: StatusValue? = null,
    var hinh: String? = null,
    var media: MutableList<MediaDTO>? = null,
    var buttons: MutableList<ButtonDetailBuy>? = null,
    var thuocTinh: MutableList<StatusValue>? = null
)
