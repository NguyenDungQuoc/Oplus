package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
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
                if(pageIndex == 1 ){
                    inventoryAdapter?.setData(listDevice)
                }else {
                    inventoryAdapter?.insertData(listDevice)
                }
            }
        })
    }
    fun getData(){
        inventoryViewModel?.searchItem(edtSearch.text.toString(), pageIndex)
    }

    private fun onClickEvent() {
        ivClose.setOnClickListener {
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
    }


}


