package com.example.oplus

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class CustomProgressDialog : AlertDialog {
    constructor(context: Context, theme: Int) : super(context, theme)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_loading)
    }
}

