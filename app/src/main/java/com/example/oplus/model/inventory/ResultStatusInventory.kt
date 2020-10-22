package com.example.oplus.model.inventory

import com.example.oplus.model.general.StatusValue

data class ResultStatusInventory (
    var daHet: StatusValue? = null,
    var sapHet : StatusValue? = null,
    var lichMua: StatusValue? = null,
    var choXacNhan: StatusValue? = null

)