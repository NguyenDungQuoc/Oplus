package com.example.oplus

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.adapter.MenuAdapter
import com.example.oplus.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var menuViewModel: MenuViewModel? = null
    private var menuAdapter: MenuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)

        recycleViewMenuDashBoard()
        menuViewModel?.getMenu()


    }

    private fun recycleViewMenuDashBoard() {
        rvMenuDashBoard.layoutManager = GridLayoutManager(this, 2)
        rvMenuDashBoard.setHasFixedSize(true)
        menuAdapter = MenuAdapter(mutableListOf())
        menuViewModel?.result?.observe(this, {
            val listMenu = it?.Result?.Items
            listMenu?.let {
                menuAdapter?.setData(listMenu)
            }
        })
        rvMenuDashBoard.adapter = menuAdapter
        with(rvMenuDashBoard){
            val decorationVertical = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
            val decorationHorizontal = DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL)
            decorationVertical.setDrawable(context.resources.getDrawable(R.drawable.line_decoration_menu_dashboard))
            decorationHorizontal.setDrawable(context.resources.getDrawable(R.drawable.line_decoration_menu_dashboard))
            addItemDecoration(decorationVertical)
            addItemDecoration(decorationHorizontal)
        }
        //get height recycleView
        rvMenuDashBoard.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                val height = rvMenuDashBoard.height
                menuAdapter?.parentHeight = height
                rvMenuDashBoard.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

}