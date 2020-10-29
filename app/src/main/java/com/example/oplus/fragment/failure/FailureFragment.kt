package com.example.oplus.fragment.failure

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.TabAdapter
import com.example.oplus.fragment.BaseTaskFragment
import com.example.oplus.viewmodel.FailureViewModel
import kotlinx.android.synthetic.main.fragment_base_task.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class FailureFragment : BaseTaskFragment() {
    private var failureViewModel:FailureViewModel? = null
    private var tabAdapter:TabAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        failureViewModel?.soLuongCongViec()

        tlTaskButton.withViewPager(vpTask)
        failureViewModel?.task?.observe(viewLifecycleOwner, {
            tabAdapter = it?.let { it1 -> TabAdapter(childFragmentManager, it1) }
            if (it != null) {
                tlTaskButton.listHeader = it
            }
            loadingDialog?.hide()
            vpTask.adapter = tabAdapter
        })

        listenerEvent()
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
