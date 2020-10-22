package com.example.oplus.model.inventory

import android.os.Parcelable
import com.example.oplus.model.general.StatusValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FarmDevice (
    var listName:String? = null,
    var itemId:Int? = null,
    var title:String? = null,
    var thumbnail:String? = null,
    var loai: StatusValue? = null,
    var soLuongTon: StatusValue? = null,
    var ma: StatusValue? = null
) : Parcelable
