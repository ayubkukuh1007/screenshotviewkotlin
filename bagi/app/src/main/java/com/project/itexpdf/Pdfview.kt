package com.project.itexpdf

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_pdfview.*
import java.io.File


class Pdfview : AppCompatActivity() {

    lateinit var screenshot : Screenshot
    lateinit var sharescreenshot: Sharescreenshot
    lateinit var constsView : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)

        val filePDF = File(this.getExternalFilesDir(null)!!.absolutePath + "/Umroh/PesertaUmroh.pdf")

        pdfView.fromFile(filePDF)
            .enableSwipe(true)
            .swipeHorizontal(true)
            .load()

        constsView = findViewById(R.id.ConstsView)
        screenshot = Screenshot(this)
        sharescreenshot = Sharescreenshot()

    }

    fun ambilscreenshot(){
        val bitmap: Bitmap? = screenshot.getViewScreenshot(
            constsView,
            constsView.height,
            constsView.width
        )
        bitmap?.let { screenshot.saveScreenshot(it) };
    }

    fun bagiscreenshot() {
        val fileScreenshot = File(this.getExternalFilesDir(null)!!.absolutePath + "/Screenshot/Screenshot.jpg")
        startActivity(sharescreenshot.ShareFileScreensshot(fileScreenshot))
    }

    fun lihatfilescreenshot(){
        startActivity(Intent(this, Showscreenshot::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.screenshot -> {
                ambilscreenshot()
                return true
            }
            R.id.showscreenshot -> {
                lihatfilescreenshot()
                return true
            }
            R.id.sharescreenshot -> {
                bagiscreenshot()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
