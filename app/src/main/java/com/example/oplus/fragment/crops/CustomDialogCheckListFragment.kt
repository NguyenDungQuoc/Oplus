package com.example.oplus.fragment.crops

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oplus.R
import com.example.oplus.adapter.VatTuAdapter
import com.example.oplus.model.crop.VatTuDTO
import kotlinx.android.synthetic.main.custom_dialog_item_checklist.*
import kotlinx.android.synthetic.main.custom_dialog_item_checklist.view.*

class CustomDialogCheckListFragment : DialogFragment() {
    var listVatTu: MutableList<VatTuDTO>? = null
    var vatTuAdapter: VatTuAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_dialog_item_checklist, container, false)
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vatTuAdapter = VatTuAdapter(mutableListOf())
        rvDSVT.layoutManager = LinearLayoutManager(activity)
        rvDSVT.setHasFixedSize(true)
        rvDSVT.adapter = vatTuAdapter
        listVatTu?.let { vatTuAdapter?.insertData(it) }
        view.imgCloseDSVT.setOnClickListener {
            dismiss()
        }
        view.imgDong.setOnClickListener {
            dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }
}