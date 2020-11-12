package com.example.oplus.fragment.giamsat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.activities.crop.DetailWorkCropActivity
import com.example.oplus.adapter.FailureInGiamSatAdapter
import com.example.oplus.viewmodel.GiamSatViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_show_failure.view.*


class BottomShowFailureFragment : BottomSheetDialogFragment() {
    var failureInGiamSatAdapter: FailureInGiamSatAdapter? = null
    var itemID = ""
    var giamSatViewModel: GiamSatViewModel? = null
    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_bottom_show_failure, container,
            false
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giamSatViewModel = ViewModelProviders.of(this).get(GiamSatViewModel::class.java)

        giamSatViewModel?.congViecTheoThietBi(itemID)

        failureInGiamSatAdapter = FailureInGiamSatAdapter(mutableListOf())

        view.rvFailure.layoutManager = LinearLayoutManager(activity)
        view.rvFailure.setHasFixedSize(true)
        view.rvFailure.adapter = failureInGiamSatAdapter

        view.imgDongSheet.setOnClickListener {
            dismiss()
        }
        observer()
        onClickEvent()
    }

    private fun onClickEvent() {
        failureInGiamSatAdapter?.onClick = {
            val intent = Intent(this.context, DetailWorkCropActivity::class.java)
            intent.putExtra("ID", it?.iD)
            startActivity(intent)
        }
    }

    private fun observer() {
        giamSatViewModel?.workDay?.observe(viewLifecycleOwner, {
            failureInGiamSatAdapter?.insertData(it)
        })
    }

}
