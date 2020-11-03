package com.example.oplus.activities


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.oplus.R
import com.example.oplus.model.failure.MediaDTO
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_zoom_img.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ZoomImageActivity : BaseActivity() {
    var item: MediaDTO? = null
    var bitmap: Bitmap? = null
    var isSave:Boolean? = false
    override fun getResource(): Int {
        return R.layout.activity_zoom_img
    }

    override fun getBackImage(): View? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initView() {
        super.initView()
        hideLoading()
        item = MediaDTO()
        item = intent.getParcelableExtra("IMAGE")
        Glide.with(pvImg.context).load(item?.fullUrl).into(pvImg)
        tvUpdateTime.text = item?.uploadDate
        imgClose.setOnClickListener {
            onBackPressed()
        }
        tvXoa.isEnabled = false



        tvDownloadImg.setOnClickListener {
            Glide.with(pvImg.context)
                .asBitmap()
                .load(item?.fullUrl)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmap = resource
                        //Xin quyen ghi vao bo nho trong
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED
                        ) {
                            saveToInternalStorage(resource)
                        } else {
                            requestStoragePermission()
                        }
                    }
                })
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 5) {
            if (grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bitmap?.let { saveToInternalStorage(it) }
            } else {

            }
        }
    }
    private fun requestStoragePermission() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                // check if all permissions are granted
                if (report.areAllPermissionsGranted()) {
                    bitmap?.let { saveToInternalStorage(it) }
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
                this,
                "Error occurred! ",
                Toast.LENGTH_SHORT
            ).show()
        }
            .onSameThread()
            .check()
    }



    private fun saveToInternalStorage(resource: Bitmap) {
        //tao parent folder
        val path =
            "${Environment.getExternalStorageDirectory().path}/${getString(R.string.app_name)}"
        val parentPath = File(path)
        if (!parentPath.exists()) {
            val isParentCreated = parentPath.mkdir()
            Log.d("", "")
        }
        //tao file
        val name = item?.uploadDate?.replace(" ", "_")
        val file = File(path, name + item?.extension)
        if (!file.exists()) {
            val isFileCreated = file.createNewFile()
            Log.d("path", file.toString())
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                resource.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                Toast.makeText(this,"Lưu ảnh thành công",Toast.LENGTH_SHORT).show()
                fos.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            var scanner: MediaScannerConnection? = null
            scanner = MediaScannerConnection(
                this,
                object : MediaScannerConnection.MediaScannerConnectionClient {
                    override fun onScanCompleted(p0: String?, p1: Uri?) {
                        Log.d("", "")
                    }

                    override fun onMediaScannerConnected() {
                        scanner?.scanFile(file.absolutePath, "jpg")
                    }
                })
            scanner.connect()
        }
    }
}