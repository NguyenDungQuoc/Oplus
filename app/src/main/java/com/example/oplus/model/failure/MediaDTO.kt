package com.example.oplus.model.failure

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MediaDTO(
    var fullUrl: String? = null,
    var relativeUrl: String? = null,
    var thumb: String? = null,
    var type: String? = null,
    var extension: String? = null,
    var uploadDate: String? = null,
) : Parcelable