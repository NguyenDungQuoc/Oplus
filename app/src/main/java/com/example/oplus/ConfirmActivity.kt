package com.example.oplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        menuTop()

    }

    private fun menuTop() {
        tvTitleMenu.text = "XÁC NHẬN LỊCH MUA"
        imgSearch.visibility = View.GONE
        imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}