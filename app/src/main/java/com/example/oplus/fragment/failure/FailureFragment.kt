package com.example.oplus.fragment.failure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.TabAdapter
import com.example.oplus.adapter.buttonUnderToolbar.ButtonUnderToolbarAdapter
import com.example.oplus.fragment.BaseTaskFragment
import com.example.oplus.viewmodel.FailureViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_base_task.*
import kotlinx.android.synthetic.main.row_button_confirm.*
import kotlinx.android.synthetic.main.row_button_confirm.view.*
import kotlinx.android.synthetic.main.row_dashboard.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*


class FailureFragment : BaseTaskFragment() {
    private var failureViewModel:FailureViewModel? = null
    private var taskFragment:TaskFragment = TaskFragment()
    private var tabAdapter:TabAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        failureViewModel?.soLuongCongViec()
        tabAdapter = TabAdapter(childFragmentManager)
        tlTaskButton.withViewPager(vpTask)
        failureViewModel?.task?.observe(viewLifecycleOwner, {
            tlTaskButton.listHeader = it
            loadingDialog?.hide()
        })
        tabAdapter?.addFragment(taskFragment)
        vpTask.adapter = tabAdapter
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
