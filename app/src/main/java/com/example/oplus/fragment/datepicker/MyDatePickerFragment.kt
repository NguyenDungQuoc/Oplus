package com.example.oplus.fragment.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import java.util.*

class MyDatePickerFragment : DialogFragment() {
    var result: ((Date) -> Unit)? = null

    companion object {
        const val KEY_TIME_STAMP = "KEY_TIME_STAMP"
        fun showPicker(fm: FragmentManager, timestamp: Long): MyDatePickerFragment {
            val newFragment = MyDatePickerFragment()
            val args = Bundle()
            args.putLong(KEY_TIME_STAMP, timestamp)
            newFragment.arguments = args
            newFragment.show(fm, "date picker")
            return newFragment
        }
    }

    fun setResultListener(result: ((Date) -> Unit)?) {
        this.result = result
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        result?.invoke(calendar.time)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timestamp = arguments?.getLong(KEY_TIME_STAMP) ?: 0
        val c = Calendar.getInstance()
        if (timestamp != 0L) {
            c.time = Date(timestamp)
        }
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext() , dateSetListener, year, month, day)
    }
}