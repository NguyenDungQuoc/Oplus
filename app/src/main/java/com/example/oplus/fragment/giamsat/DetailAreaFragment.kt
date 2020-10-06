package com.example.oplus.fragment.giamsat


import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.ListItemForAreaAdapter
import com.example.oplus.fragment.BaseFragment
import com.example.oplus.fragment.dialogcustom.InfoDialogCustomFragment
import com.example.oplus.model.giamsat.GiamSatItem
import com.example.oplus.viewmodel.GiamSatViewModel
import kotlinx.android.synthetic.main.fragment_giamsat_detail.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import java.util.*

class DetailAreaFragment : BaseFragment(R.layout.fragment_detail_area) {
    var listItemForAreaAdapter:ListItemForAreaAdapter? = null
    var itemGiamSat: GiamSatItem? = null
    private var giamSatViewModel: GiamSatViewModel? =  null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fbCamera.drawable.mutate()
            .setTint(ContextCompat.getColor(activity as MainActivity, R.color.white))
        createToolbarMenu()
        getWidthFromParent()
        giamSatViewModel = ViewModelProviders.of(this).get(GiamSatViewModel::class.java)
        itemGiamSat?.title?.let { giamSatViewModel?.getListThietBi(it) }
        createRecyclerView()
        observe()
    }

    private fun observe() {
        giamSatViewModel?.itemForArea?.observe(viewLifecycleOwner, {
            val list = it?.result?.items
            listItemForAreaAdapter?.setData(list ?: mutableListOf())
            loadingDialog?.hide()
        })
    }

    private fun createRecyclerView() {
        listItemForAreaAdapter = ListItemForAreaAdapter(mutableListOf())
        val layoutManager = GridLayoutManager(activity, 7)
        rvGiamSatItemDetail.layoutManager = layoutManager
        rvGiamSatItemDetail.setHasFixedSize(true)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val soDu = position % 5
                if (soDu % 2 == 0) {
                    return 1
                } else {
                    return 2
                }
            }
        }

        rvGiamSatItemDetail.adapter = listItemForAreaAdapter
    }

    private fun getWidthFromParent() {
        giamSat.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val width = giamSat.width
                listItemForAreaAdapter?.parentWidth = width
                giamSat.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun createToolbarMenu() {
        tvTitleMenu.text = itemGiamSat?.title?.toUpperCase(Locale.ROOT)
        imgSearch.setImageResource(R.drawable.icon_info)
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        imgSearch.setOnClickListener {
            fragmentManager?.let { it1 ->
                InfoDialogCustomFragment.newInstance(
                    "getString(R.string.accept)",
                    "getString(R.string.accept)"
                ).show(
                    it1, "String"
                )
            }
        }
    }
}