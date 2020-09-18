package com.example.oplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmDevice (
    var ListName:String? = null,
    var ItemId:Int? = null,
    var Title:String? = null,
    var Thumbnail:String? = null,
    var Loai: StatusInventory? = null,
    var SoLuongTon: StatusInventory? = null,
    var Ma: StatusInventory? = null
) : Parcelable
