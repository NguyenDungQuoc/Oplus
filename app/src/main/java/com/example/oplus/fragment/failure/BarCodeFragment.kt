package com.example.oplus.fragment.failure

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.activities.BaseDetailActivity
import com.example.oplus.activities.DetailDeviceActivity
import com.example.oplus.activities.MainActivity
import com.example.oplus.customview.ViewFinderView
import com.example.oplus.model.QRCodeFailureDTO
import com.example.oplus.model.inventory.FarmDevice
import com.google.gson.Gson
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_bar_code.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarCodeFragment : Fragment(R.layout.fragment_bar_code), ZXingScannerView.ResultHandler {
    private val FLASH_STATE = "FLASH_STATE"
    private var mFlash = false
    private var mScannerView: ZXingScannerView? = null
    private var isFlashOn: Boolean = false
    private var device: FarmDevice? = null
    var type = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultView()
        requestStoragePermission()
        mScannerView = object : ZXingScannerView(context) {
            override fun createViewFinderView(context: Context): IViewFinder {
                return CustomViewFinderView(context)
            }
        }
        contentFrame.addView(mScannerView)
        lnFlash.setOnClickListener {
            mFlash = !mFlash
            mScannerView?.flash = mFlash
            isFlashOn = !isFlashOn
            if (isFlashOn) {
                tvFlash.text = getString(R.string.offFlash).toUpperCase()
            } else {
                tvFlash.text = getString(R.string.onFlash).toUpperCase()
            }
        }
    }

    private fun defaultView() {
        tvTitleMenu.text = getString(R.string.qrCodeTitle).toUpperCase()
        tvFlash.text = getString(R.string.onFlash).toUpperCase()

        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        imgSearch.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        // You can optionally set aspect ratio tolerance level
        // that is used in calculating the optimal Camera preview size
        mScannerView?.setAspectTolerance(0.2f)
        mScannerView?.startCamera()
        mScannerView?.flash = mFlash
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FLASH_STATE, mFlash)
    }


    override fun handleResult(rawResult: Result?) {

        val item = Gson().fromJson(rawResult?.text, QRCodeFailureDTO::class.java)
        if(type == ScreenIDEnum.FAILURE_SCREEN.value){
            val intent = Intent(this.context, BaseDetailActivity::class.java)
            intent.putExtra("ID", item.ItemID).putExtra("TYPE", type)
            startActivity(intent)
        }else{
            device = FarmDevice().apply {
                listName = item.ListName
                itemId = item.ItemID
            }

            val intent= Intent(activity, DetailDeviceActivity::class.java)
            intent.putExtra("DATA", device).putExtra("TYPE", type)
            startActivity(intent)
        }

        // If you would like to resume scanning, call this method below:
        mScannerView?.resumeCameraPreview(this)
    }


    private class CustomViewFinderView : ViewFinderView {
        val PAINT = Paint()

        constructor(context: Context?) : super(context) {
            init()
        }

        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
            init()
        }

        private fun init() {
            PAINT.color = Color.WHITE
            PAINT.isAntiAlias = true
            val textPixelSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                TRADE_MARK_TEXT_SIZE_SP.toFloat(), resources.displayMetrics
            )
            PAINT.textSize = textPixelSize
            setSquareViewFinder(true)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            drawTradeMark(canvas)
        }

        private fun drawTradeMark(canvas: Canvas) {
            val framingRect = framingRect
            val tradeMarkTop: Float
            val tradeMarkLeft: Float
            if (framingRect != null) {
                tradeMarkTop = framingRect.bottom + PAINT.textSize + 10
                tradeMarkLeft = framingRect.left.toFloat()
            } else {
                tradeMarkTop = 10f
                tradeMarkLeft = canvas.height - PAINT.textSize - 10
            }
            canvas.drawText(TRADE_MARK_TEXT, tradeMarkLeft, tradeMarkTop, PAINT)
        }

        companion object {
            const val TRADE_MARK_TEXT = "Di chuyển Camera để thấy rõ mã QR"
            const val TRADE_MARK_TEXT_SIZE_SP = 14
        }
    }

    private fun requestStoragePermission() {

        Dexter.withActivity(activity).withPermissions(
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                // check if all permissions are granted
                if (report.areAllPermissionsGranted()) {

                }
                // check for permanent denial of any permission
                if (report.isAnyPermissionPermanentlyDenied) {
                    // show alert dialog navigating to Settings
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener { error ->
            Toast.makeText(
                context,
                "Error occurred! ",
                Toast.LENGTH_SHORT
            ).show()
        }
            .onSameThread()
            .check()
    }
}
