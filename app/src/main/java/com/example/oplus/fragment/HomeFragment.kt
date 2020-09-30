package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.MenuAdapter
import com.example.oplus.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var menuViewModel: MenuViewModel? = null
    private var menuAdapter: MenuAdapter? = null
    private var inventoryFragment: InventoryFragment = InventoryFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        menuViewModel?.getMenu()
        recycleViewMenuDashBoard()
        getHeight()
        onClick()
    }

    private fun onClick() {
        menuAdapter?.onClick = {
            when(it?.webUrl) {
                getString(R.string.tk) -> {
                    (activity as MainActivity).showFragment(inventoryFragment,true)
                }
            }
        }
    }

    private fun getHeight() {
        //get height recycleView
        rvMenuDashBoard.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val height = rvMenuDashBoard.height
                menuAdapter?.parentHeight = height
                rvMenuDashBoard.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    private fun recycleViewMenuDashBoard() {

        rvMenuDashBoard.layoutManager = GridLayoutManager(activity, 2)
        rvMenuDashBoard.setHasFixedSize(true)
        menuAdapter = MenuAdapter(mutableListOf())
        menuViewModel?.result?.observe(viewLifecycleOwner, {
            val listMenu = it?.result?.items
            listMenu?.let {
                menuAdapter?.setData(listMenu)
            }
        })
        rvMenuDashBoard.adapter = menuAdapter
        with(rvMenuDashBoard) {
            val decorationVertical = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            val decorationHorizontal =
                DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
            decorationVertical.setDrawable(context.resources.getDrawable(R.drawable.line_decoration_menu_dashboard))
            decorationHorizontal.setDrawable(context.resources.getDrawable(R.drawable.line_decoration_menu_dashboard))
            addItemDecoration(decorationVertical)
            addItemDecoration(decorationHorizontal)
        }



    }
}