package com.example.myapplication4_2

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextInput: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupClickListener()

        // Register the TextView for the context menu
        registerForContextMenu(textViewResult)
    }

    private fun initializeViews() {
        editTextInput = findViewById(R.id.editText)
        buttonSubmit = findViewById(R.id.button)
        textViewResult = findViewById(R.id.text)
    }

    private fun setupClickListener() {
        buttonSubmit.setOnClickListener {
            val inputText = editTextInput.text.toString()
            textViewResult.text = inputText
            showConfirmationDialog()  // Show confirmation dialog when button is clicked
        }
    }

    private fun showError(viewName: String) {
        Toast.makeText(this, "Error: $viewName not found in layout", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.color_red -> setBackgroundColor(R.color.red)
            R.id.color_blue -> setBackgroundColor(R.color.blue)
            R.id.color_green -> setBackgroundColor(R.color.green)
            R.id.color_yellow -> setBackgroundColor(R.color.yellow)
            R.id.color_purple -> setBackgroundColor(R.color.purple)
            R.id.color_orange -> setBackgroundColor(R.color.orange)
            R.id.color_pink -> setBackgroundColor(R.color.pink)
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setBackgroundColor(colorRes: Int): Boolean {
        window.decorView.setBackgroundColor(ContextCompat.getColor(this, colorRes))
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)  // Inflate context menu for TextView
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.color_red -> setTextViewTextColor(R.color.red)
            R.id.color_blue -> setTextViewTextColor(R.color.blue)
            R.id.color_green -> setTextViewTextColor(R.color.green)
            R.id.color_yellow -> setTextViewTextColor(R.color.yellow)
            R.id.color_purple -> setTextViewTextColor(R.color.purple)
            R.id.color_orange -> setTextViewTextColor(R.color.orange)
            R.id.color_pink -> setTextViewTextColor(R.color.pink)
            else -> super.onContextItemSelected(item)
        }
    }

    private fun setTextViewTextColor(colorRes: Int): Boolean {
        textViewResult.setTextColor(ContextCompat.getColor(this, colorRes))
        return true
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Do you want to proceed to the next activity?")
            .setPositiveButton("Yes") { dialog, _ ->
                // Send the text to AnotherActivity
                val intent = Intent(this, AnotherActivity::class.java)
                intent.putExtra("EXTRA_TEXT", textViewResult.text.toString())
                startActivity(intent)  // Start AnotherActivity
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
