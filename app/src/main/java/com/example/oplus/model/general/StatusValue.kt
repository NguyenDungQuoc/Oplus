package com.example.oplus.model.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatusValue(
    var title: String? = null,
    var value: String? = null,
    var key: String? = null,
    var iD: Int? = null,
    var Icon: String? = null
) : Parcelable