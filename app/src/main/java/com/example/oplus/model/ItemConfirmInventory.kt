package com.example.oplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemConfirmInventory(
    var listName: String? = null,
    var itemId: Int? = null,
    var title: String? = null,
    var xacNhan: String? = null,
    var soLuongTon: StatusInventory? = null,
    var loai: StatusInventory? = null,
    var ma: StatusInventory? = null,
    var donViTinh: StatusInventory? = null,
    var thumbnail: String? = null,
    var listThongtin: MutableList<StatusInventory>? = null,
    var listHinh:MutableList<String>? = null
) : Parcelable
