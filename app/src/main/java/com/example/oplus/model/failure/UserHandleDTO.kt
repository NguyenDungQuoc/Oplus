package com.example.oplus.model.failure

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserHandleDTO(
    var title: String? = null,
    var iD: Int? = null,
    var value: String? = null,
    var email: String? = null,
    var avatar: String? = null,
) : Parcelable