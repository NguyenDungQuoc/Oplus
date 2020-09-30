package com.example.oplus.model.base

import com.example.oplus.model.CurrentUserProfile
import com.example.oplus.model.inventory.ResultStatusInventory
import com.example.oplus.model.login.ResultLogin

object Base {
    var loginData: ResultLogin? = null
    var currentUserProfile: CurrentUserProfile? = null
    var statusStatusInventory: ResultStatusInventory? = null
}