package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.MenuAdapter
import com.example.oplus.decoration.DecoWithoutLeftRight
import com.example.oplus.fragment.failure.FailureFragment
import com.example.oplus.fragment.giamsat.GiamSatFragment
import com.example.oplus.fragment.inventory.InventoryFragment
import com.example.oplus.model.ItemResultMenu
import kotlinx.android.synthetic.main.fragment_dashboard_rv.*
import java.io.Serializable

class DashBoardMenuFragment : Fragment(R.layout.fragment_dashboard_rv) {
    companion object {
        private const val KEY_DATA = "KEY_DATA"
        fun newInstance(list: MutableList<ItemResultMenu>): DashBoardMenuFragment {
            var menuFragment = DashBoardMenuFragment()
            var bundle = Bundle()
            bundle.putSerializable(KEY_DATA, list as Serializable)
            menuFragment.arguments = bundle
            return menuFragment
        }
    }

    private var inventoryFragment: InventoryFragment = InventoryFragment()
    private var giamSatFragment: GiamSatFragment = GiamSatFragment()
    private var failureFragment:FailureFragment? = null
    private var menuAdapter: MenuAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewMenuDashBoard()
        getHeight()
        onClick()
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
        val listData = (arguments?.getSerializable(KEY_DATA) as MutableList<ItemResultMenu>)

        menuAdapter?.setData(listData)

        rvMenuDashBoard.adapter = menuAdapter
        rvMenuDashBoard.addItemDecoration(
            DecoWithoutLeftRight(
                context?.resources?.getDimensionPixelSize(
                    R.dimen.height_line_size
                ) ?: 1
            )
        )
    }

    private fun onClick() {
        menuAdapter?.onClick = {
            when (it?.webUrl) {
                getString(R.string.tk) -> {
                    (activity as MainActivity).showFragment(inventoryFragment, true)
                }
                "gs" -> {
                    (activity as MainActivity).showFragment(giamSatFragment, true)
                }
                "sc" -> {
                    failureFragment = FailureFragment()
                    (activity as MainActivity).showFragment(failureFragment!!, true)
                }
            }
        }
    }
}