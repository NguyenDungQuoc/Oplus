package com.example.oplus.fragment.base


import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.ClusterAdapter

import com.example.oplus.adapter.DayWorkAdapter
import com.example.oplus.customview.CenterLayoutManager
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.AdoptFishViewModel
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel
import com.example.oplus.viewmodel.FailureViewModel
import com.khieu.nguyen.expandablecalendar.data.Day
import com.khieu.nguyen.expandablecalendar.widget.EOCalendar
import com.khieu.nguyen.expandablecalendar.widget.UIEOCalendar
import kotlinx.android.synthetic.main.fragment_task.*

abstract class BaseTaskSNCFragment(val tabName: String) : BaseFragment(R.layout.fragment_task),
    EOCalendar.CalendarEventListener {
    var failureViewModel: FailureViewModel? = null
    var adoptFishViewModel: AdoptFishViewModel? = null
    var cropsViewModel: CropsViewModel? = null
    var dayWorkAdapter: DayWorkAdapter? = null
    var request: TaskRequestDTO? = null
    var clusterAdapter: ClusterAdapter? = null

    override fun initView() {
        super.initView()

        calendarView.currentType = UIEOCalendar.TYPE_WEEK
        calendarView.initContent()
        calendarView.calendarEventListener = this
        recyclerView()
        observer()
        onClickEvent()
//        requestByMonth()

    }

    abstract fun getTypeScreen(): String
    private fun onClickEvent() {

    }

    private fun observer() {
        getViewModel().workDay.observe(viewLifecycleOwner, {
            dayWorkAdapter?.insertData(it)
        })
        getViewModel().cluster?.observe(viewLifecycleOwner, {
            clusterAdapter?.bindingData(it ?: mutableListOf())
        })
    }

    abstract override fun getViewModel(): BaseTaskViewModel

    private fun recyclerView() {
        rvWork.layoutManager = LinearLayoutManager(activity)
        rvWork.setHasFixedSize(true)
        dayWorkAdapter = DayWorkAdapter(mutableListOf())
        dayWorkAdapter?.type = getTypeScreen()
        rvWork.adapter = dayWorkAdapter

        rvListCluster.layoutManager = CenterLayoutManager(context,CenterLayoutManager.HORIZONTAL,false)
        rvListCluster.setHasFixedSize(true)
        clusterAdapter = ClusterAdapter(mutableListOf())
        rvListCluster.adapter = clusterAdapter

    }

    override fun onMonthChange(month: Int) {
        requestByMonth()
    }

    override fun onSelectDate(day: Day) {
//        requestByDate()
    }

    override fun onWeekChange(firstDayOffWeek: Day?) {
//        requestByDate()
    }


    private fun requestByMonth() {
        request = TaskRequestDTO()
        request?.tabName = tabName
        val ngay = calendarView.selectedDay
        request?.thang = ngay?.month?.plus(1)
        request?.nam = ngay?.year
        request?.let { failureViewModel?.congViecTheoThang(it) }
    }
}