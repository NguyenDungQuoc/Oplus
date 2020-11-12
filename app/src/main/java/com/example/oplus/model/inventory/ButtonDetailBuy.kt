package com.example.oplus.model.inventory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ButtonDetailBuy (
    var title:String? = null,
    var aPI:String? = null,
    var maMau:String? = null,
    var enable:Boolean = false
) : Parcelable
