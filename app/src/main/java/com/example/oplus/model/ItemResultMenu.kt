package com.example.oplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemResultMenu (
    var title:String? = null,
    var imageUrl:String? = null,
    var webUrl:String? = null,
    var search:Boolean? = false,
    var screenId:String? = null,
    var totalTask:Int? = null
): Parcelable
