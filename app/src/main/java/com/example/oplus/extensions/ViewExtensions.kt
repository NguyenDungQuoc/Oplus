package com.example.oplus.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.oplus.R

fun Spinner.setList(list: MutableList<String?>?, selected: Int?) {
    list?.let {
        val dataAdapter = ArrayAdapter<String>(
            context,
            R.layout.row_spinner, list
        )
        dataAdapter.setDropDownViewResource(R.layout.row_spinner)
        adapter = dataAdapter
        if (selected != null && selected != -1) {
            setSelection(selected)
        }
    }
}
fun Spinner.selectedItemListener(color: Int? = null, listener: (Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            view?.let {
                if (view is TextView) {
                    color?.let {
                        view.setTextColor(it)
                    }
                }
            }
            listener.invoke(position)
        }

    }
}