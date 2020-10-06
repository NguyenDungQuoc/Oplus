package com.example.oplus.model.giamsat

import java.io.Serializable

data class GiamSatItem (
    var id:Int? = null,
    var thumbnail:String? = null,
    var ma:String? = null,
    var title:String? = null,
    var tasks:String? = null
) : Serializable
