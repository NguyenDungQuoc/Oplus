package com.example.oplus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusInventory (
    var Title:String? = null,
    var Value:String? = null
): Parcelable