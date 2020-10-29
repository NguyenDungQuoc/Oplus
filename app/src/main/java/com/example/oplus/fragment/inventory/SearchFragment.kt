package com.example.oplus.fragment.inventory

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.InventoryAdapter
import com.example.oplus.viewmodel.InventoryViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.toolbar_search.*


class SearchFragment : Fragment(R.layout.fragment_search) {
    private var inventoryViewModel: InventoryViewModel? = null
    private var inventoryAdapter: InventoryAdapter? = null
    var pageIndex = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        observe()
        createRecycleView()
        ivClear.visibility = View.GONE
        onClickEvent()
    }

    private fun createRecycleView() {
        inventoryAdapter = InventoryAdapter(mutableListOf())
        rvListDeviceSearch.layoutManager = GridLayoutManager(activity, 1)
        rvListDeviceSearch.setHasFixedSize(true)
        rvListDeviceSearch.adapter = inventoryAdapter
        rvListDeviceSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    pageIndex += 1
                    getData()
                }
            }
        })
    }

    private fun observe() {
        inventoryViewModel?.deviceSearch?.observe(viewLifecycleOwner, {
            val listDevice = it?.result?.items
            listDevice?.let {
                if (pageIndex == 1) {
                    inventoryAdapter?.setData(listDevice)
                } else {
                    inventoryAdapter?.insertData(listDevice)
                }
            }
        })
    }
    fun getData(){
        inventoryViewModel?.searchItem(edtSearch.text.toString(), pageIndex)
    }
    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun onClickEvent() {
        ivClose.setOnClickListener {
            hideKeyboardFrom(activity as MainActivity,edtSearch)
            (activity as MainActivity).onBackPressed()

        }
        ivClear.setOnClickListener {
            edtSearch.text?.clear()
        }
        edtSearch.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getData()
                pageIndex = 1
                true
            } else {
                false
            }
        }

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length == 0) {
                    ivClear.visibility = View.GONE
                } else {
                    ivClear.visibility = View.VISIBLE
                }
            }
        })
    }


}


