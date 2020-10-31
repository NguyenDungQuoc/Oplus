package com.example.oplus.fragment.failure

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.activities.MainActivity
import com.example.oplus.fragment.BaseTaskFragment
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class FailureFragment : BaseTaskFragment() {
    private var failureViewModel: FailureViewModel? = null

    override fun initView() {
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        failureViewModel?.soLuongCongViec()
        listenerEvent()
        super.initView()
    }

    override fun getViewModel(): BaseTaskViewModel {
        return failureViewModel!!
    }

    override fun getTitle(): String? {
        return "SỰ CỐ"
    }


    private fun listenerEvent() {
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}
