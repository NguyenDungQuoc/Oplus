package com.example.oplus.fragment.dialogcustom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.ItemInfoAdapter
import com.example.oplus.viewmodel.GiamSatViewModel
import kotlinx.android.synthetic.main.custom_dialog_info_item.*


class InfoDialogCustomFragment: DialogFragment() {
    private var itemInfoAdapter:ItemInfoAdapter? = null
    private var giamSatViewModel: GiamSatViewModel? = null
    companion object {
        const val TAG = "SimpleDialog"
        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"
        fun newInstance(title: String, subTitle: String): InfoDialogCustomFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = InfoDialogCustomFragment()
            fragment.arguments = args
            return fragment
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_info_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giamSatViewModel = ViewModelProviders.of(this).get(GiamSatViewModel::class.java)
        giamSatViewModel?.thongTinThietBi()
        itemInfoAdapter = ItemInfoAdapter(mutableListOf())
        giamSatViewModel?.itemInfo?.observe(viewLifecycleOwner,{
            val listITem = it?.result?.items
            itemInfoAdapter?.setData(listITem ?: mutableListOf())
        })
        rvInfoITem.layoutManager = GridLayoutManager(activity, 1)
        rvInfoITem.setHasFixedSize(true)
        rvInfoITem.adapter = itemInfoAdapter
        setupView(view)
        setupClickListeners(view)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
    private fun setupView(view: View) {
//        view.tvTitle.text = arguments?.getString(KEY_TITLE)
//        view.tvSubTitle.text = arguments?.getString(KEY_SUBTITLE)
    }

    private fun setupClickListeners(view: View) {
        view.let {
            tvBtnClose.setOnClickListener {
                dismiss()
            }
        }
//        view.btnPositive.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//        }
    }
}