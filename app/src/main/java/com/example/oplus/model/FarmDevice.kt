package com.example.oplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmDevice (
    var listName:String? = null,
    var itemId:Int? = null,
    var title:String? = null,
    var thumbnail:String? = null,
    var loai: StatusInventory? = null,
    var soLuongTon: StatusInventory? = null,
    var ma: StatusInventory? = null
) : Parcelable
