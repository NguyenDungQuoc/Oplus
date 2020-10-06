package com.example.oplus.fragment.giamsat

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.ItemGiamSatAdapter
import com.example.oplus.fragment.BaseFragment
import com.example.oplus.model.giamsat.GiamSatItem
import com.example.oplus.viewmodel.GiamSatViewModel
import kotlinx.android.synthetic.main.fragment_giamsat_detail.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

class DetailGiamSatFragment : BaseFragment(R.layout.fragment_giamsat_detail) {
    var item: GiamSatItem? = null
    private var detailAreaFragment: DetailAreaFragment = DetailAreaFragment()
    private var giamSatAdapter: ItemGiamSatAdapter? = null
    private var giamSatViewModel: GiamSatViewModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giamSatViewModel = ViewModelProviders.of(this).get(GiamSatViewModel::class.java)
        item?.id?.let { giamSatViewModel?.getListPhanKhu(it) }
        createToolbarMenu()
        createRecyclerView()
        getWidthParent()
        observe()
        onClickEvent()
    }

    private fun observe() {
        giamSatViewModel?.itemDetail?.observe(viewLifecycleOwner, {
            val listItem = it.result?.items
            listItem.let {
                giamSatAdapter?.setData(listItem ?: mutableListOf())
            }
            loadingDialog?.hide()
        })
    }

    private fun onClickEvent() {
        giamSatAdapter?.onClick = {
            detailAreaFragment.itemGiamSat = it
            (activity as MainActivity).showFragment(detailAreaFragment, true)
        }
    }

    private fun getWidthParent() {
        rvGiamSatItemDetail.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = rvGiamSatItemDetail.width
                giamSatAdapter?.parentWidth = width
                rvGiamSatItemDetail.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

        })
    }

    private fun createRecyclerView() {
        rvGiamSatItemDetail.layoutManager = GridLayoutManager(activity, 2)
        rvGiamSatItemDetail.setHasFixedSize(true)
        giamSatAdapter = ItemGiamSatAdapter(mutableListOf())

        rvGiamSatItemDetail.adapter = giamSatAdapter
        fbCamera.drawable.mutate()
            .setTint(ContextCompat.getColor(activity as MainActivity, R.color.white))
    }

    private fun createToolbarMenu() {
        tvTitleMenu.text = item?.title?.toUpperCase()
        imgSearch.visibility = View.GONE
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        imgSearch.setOnClickListener {

        }
    }
}