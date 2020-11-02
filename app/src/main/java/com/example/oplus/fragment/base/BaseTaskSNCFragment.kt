package com.example.oplus.fragment.base

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.activities.BaseDetailActivity
import com.example.oplus.adapter.DayWorkAdapter
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.AdoptFishViewModel
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel
import com.example.oplus.viewmodel.FailureViewModel
import com.khieu.nguyen.expandablecalendar.data.Day
import com.khieu.nguyen.expandablecalendar.widget.EOCalendar
import com.khieu.nguyen.expandablecalendar.widget.UIEOCalendar
import kotlinx.android.synthetic.main.fragment_task.*
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseTaskSNCFragment(val tabName: String) : BaseFragment(R.layout.fragment_task),
    EOCalendar.CalendarEventListener {
    var failureViewModel: FailureViewModel? = null
    var adoptFishViewModel: AdoptFishViewModel? = null
    var cropsViewModel: CropsViewModel? = null
    private var dayWorkAdapter: DayWorkAdapter? = null
    var request: TaskRequestDTO? = null



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

    abstract fun getTypeScreen():String
    private fun onClickEvent() {
        dayWorkAdapter?.onClick = {
            val intent = Intent(this.context, BaseDetailActivity::class.java)
            intent.putExtra("ID", it?.iD)
            startActivity(intent)
        }
    }

    private fun observer() {
        getViewModel().workDay.observe(viewLifecycleOwner, {
            dayWorkAdapter?.insertData(it)
        })

    }
    abstract fun getViewModel(): BaseTaskViewModel

    private fun recyclerView() {
        rvWork.layoutManager = LinearLayoutManager(activity)
        rvWork.setHasFixedSize(true)
        dayWorkAdapter = DayWorkAdapter(mutableListOf())
        rvWork.adapter = dayWorkAdapter
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