package com.example.myapplication4_2

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AnotherActivity : AppCompatActivity() {
    private lateinit var textViewDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        initializeViews()
        registerForContextMenu(textViewDisplay)  // Register the TextView for context menu
        displayReceivedText()
    }

    private fun initializeViews() {
        textViewDisplay = findViewById(R.id.textViewColor)
    }

    private fun displayReceivedText() {
        // Get the intent that started this activity
        val receivedText = intent.getStringExtra("EXTRA_TEXT") ?: "No text received"
        textViewDisplay.text = receivedText
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)  // Inflate context menu
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.color_red -> setTextColor(R.color.red)
            R.id.color_blue -> setTextColor(R.color.blue)
            R.id.color_green -> setTextColor(R.color.green)
            R.id.color_yellow -> setTextColor(R.color.yellow)
            R.id.color_purple -> setTextColor(R.color.purple)
            R.id.color_orange -> setTextColor(R.color.orange)
            R.id.color_pink -> setTextColor(R.color.pink)
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setTextColor(colorRes: Int): Boolean {
        textViewDisplay.setTextColor(ContextCompat.getColor(this, colorRes))
        return true
    }
}
