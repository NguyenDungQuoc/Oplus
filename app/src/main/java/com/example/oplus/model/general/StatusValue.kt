package com.example.oplus.model.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusValue (
    var title:String? = null,
    var value:String? = null
): Parcelable