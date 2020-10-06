package com.example.oplus.fragment.giamsat

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.ItemGiamSatAdapter
import com.example.oplus.fragment.BaseFragment
import com.example.oplus.viewmodel.GiamSatViewModel
import kotlinx.android.synthetic.main.fragment_giamsat.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class GiamSatFragment: BaseFragment(R.layout.fragment_giamsat) {
    private var giamSatAdapter:ItemGiamSatAdapter? = null
    private var giamSatViewModel:GiamSatViewModel? =  null
    private var detailGiamSatFragment: DetailGiamSatFragment = DetailGiamSatFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        giamSatViewModel = ViewModelProviders.of(this).get(GiamSatViewModel::class.java)
        giamSatViewModel?.getListHe()
        createToolbarMenu()
        createRecyclerView()
        getWidthParent()
        observe()
        onClickEvent()

    }

    private fun onClickEvent() {
        giamSatAdapter?.onClick = {
            detailGiamSatFragment.item = it
            (activity as MainActivity).showFragment(detailGiamSatFragment,true)
        }
    }

    private fun observe() {
        giamSatViewModel?.item?.observe(viewLifecycleOwner,{
            val listItem = it.result?.items
            listItem.let {
                giamSatAdapter?.setData(listItem ?: mutableListOf())
            }
            loadingDialog?.hide()
        })
    }

    private fun getWidthParent() {
        rvGiamSatItem.viewTreeObserver.addOnGlobalLayoutListener (object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                val width = rvGiamSatItem.width
                giamSatAdapter?.parentWidth = width
                rvGiamSatItem.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun createRecyclerView() {
        rvGiamSatItem.layoutManager = GridLayoutManager(activity,2)
        rvGiamSatItem.setHasFixedSize(true)
        giamSatAdapter = ItemGiamSatAdapter(mutableListOf())

        rvGiamSatItem.adapter = giamSatAdapter
    }

    private fun createToolbarMenu() {
        tvTitleMenu.text = getString(R.string.giam_sat).toUpperCase()
        imgSearch.visibility = View.GONE
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}