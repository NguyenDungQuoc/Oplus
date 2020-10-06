package com.example.oplus

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

class CustomProgressDialog(context: Context, theme: Int) : AlertDialog(context, theme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_loading)
    }
}

