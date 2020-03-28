package com.project.itexpdf

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {

    lateinit var btnCetak : Button
    lateinit var btnViewPdf : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCetak = findViewById(R.id.btnCetak);
        btnViewPdf = findViewById(R.id.viewpdf);

        btnCetak.setOnClickListener { view ->
            requestStoragePermission();
        }

        btnViewPdf.setOnClickListener { view ->
            startActivity(Intent(this, Pdfview::class.java))
        }

    }

    private fun reportPDF () {
        //Dummy data
        var modelUmroh = ModelUmroh (
            "Paket Umroh Ramadhan",
            "1",
            "Approve",
            "Ayup Kukuh P",
            "081234567890",
            "7201234648363845",
            "65372653888839",
            "Approve",
            true,
            false,
            true,
            true,
            true,
            true,
            true,
            "Single",
            "Open",
            "27-03-2020",
            15000000.0,
            5000000.0,
            10000000.0
        )

        var pdf = Pdf(modelUmroh,this);

        pdf.savePDF();

        Toast.makeText(this@MainActivity, "Filepdf Oke!", Toast.LENGTH_SHORT).show()

    }

    private fun requestStoragePermission()  {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        reportPDF()
                    }
                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) { // show alert dialog navigating to Settings

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT)
                    .show()
            }
            .onSameThread()
            .check()
    }

}
