package com.example.oplus.model.inventory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusInventory (
    var title:String? = null,
    var value:String? = null
): Parcelable