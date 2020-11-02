package com.example.oplus.fragment.crops

import androidx.lifecycle.ViewModelProviders
import com.example.oplus.ScreenIDEnum
import com.example.oplus.fragment.base.BaseTaskSNCFragment
import com.example.oplus.model.failure.TaskRequestDTO
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.CropsViewModel
import com.khieu.nguyen.expandablecalendar.data.Day
import kotlinx.android.synthetic.main.fragment_task.*
import java.text.SimpleDateFormat
import java.util.*

class TaskCropsFragment(val tabNameChild: String) : BaseTaskSNCFragment(tabNameChild) {
    override fun initView() {
        cropsViewModel = ViewModelProviders.of(this).get(CropsViewModel::class.java)
        super.initView()

        requestByDate()
    }

    override fun getTypeScreen(): String {
        return ScreenIDEnum.CROP_SCREEN.value
    }

    override fun getViewModel(): BaseTaskViewModel {
        return cropsViewModel!!
    }

    override fun onSelectDate(day: Day) {
        requestByDate()
    }

    override fun onWeekChange(firstDayOffWeek: Day?) {
        requestByDate()
    }
    fun requestByDate() {
        request = TaskRequestDTO()
        request?.tabName = tabName
        val ngay = calendarView.selectedDay
        val date: Date = GregorianCalendar(ngay?.year ?: 1, ngay?.month ?: 1, ngay?.day ?: 1).time

        val format = SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH)
        request?.ngay = format.format(date).toString()
        request?.LoTrong = 0

        cropsViewModel?.congViecTheoNgay(
            request?.tabName!!,
            request?.ngay!!,
            request?.LoTrong!!
        )

    }
}