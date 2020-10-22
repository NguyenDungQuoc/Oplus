package com.example.oplus.model.inventory

import android.os.Parcelable
import com.example.oplus.model.general.StatusValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemConfirmInventory(
    var listName: String? = null,
    var itemId: Int? = null,
    var title: String? = null,
    var xacNhan: String? = null,
    var soLuongTon: StatusValue? = null,
    var loai: StatusValue? = null,
    var ma: StatusValue? = null,
    var donViTinh: StatusValue? = null,
    var thumbnail: String? = null
) : Parcelable
