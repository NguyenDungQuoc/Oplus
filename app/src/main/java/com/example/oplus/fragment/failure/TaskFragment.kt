package com.example.oplus.fragment.failure

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.DayWorkAdapter
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.FailureViewModel
import com.khieu.nguyen.expandablecalendar.data.Day
import com.khieu.nguyen.expandablecalendar.widget.EOCalendar
import com.khieu.nguyen.expandablecalendar.widget.UIEOCalendar
import kotlinx.android.synthetic.main.fragment_task.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFragment: Fragment(R.layout.fragment_task), EOCalendar.CalendarEventListener {
    private var failureViewModel: FailureViewModel? = null
    private var dayWorkAdapter:DayWorkAdapter? = null
    private var request: TaskRequestDTO? = TaskRequestDTO()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        failureViewModel = ViewModelProviders.of(this).get(FailureViewModel::class.java)
        calendarView.currentType = UIEOCalendar.TYPE_WEEK
        calendarView.initContent()
        calendarView.calendarEventListener = this
        rvWork.layoutManager = LinearLayoutManager(activity)
        rvWork.setHasFixedSize(true)
        dayWorkAdapter = DayWorkAdapter(mutableListOf())
        failureViewModel?.workDay?.observe(viewLifecycleOwner,{
            dayWorkAdapter?.insertData(it)
        })
        rvWork.adapter = dayWorkAdapter
        requestServer()
    }
    override fun onMonthChange(month: Int) {
        requestServer()
    }

    override fun onSelectDate(day: Day) {
        requestServer()
    }

    override fun onWeekChange(firstDayOffWeek: Day?) {
        requestServer()
    }
    private fun requestServer(){
        request?.tabName = "CongViec"
        val ngay = calendarView.selectedDay
        val date: Date = GregorianCalendar(ngay?.year ?: 1,ngay?.month ?: 1,ngay?.day ?:1).time

        val format = SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH)
        request?.ngay = format.format(date).toString()
        failureViewModel?.congViecTheoNgay(request?.tabName!!, request?.ngay!!)
    }
}