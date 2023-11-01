package com.example.mysecretdays

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private val openLockButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openLockButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private val firstNumberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.firstNumberPicker)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val secondNumberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.secondNumberPicker)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val thirdNumberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.thirdNumberPicker)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumberPicker
        secondNumberPicker
        thirdNumberPicker

        openLockButton.setOnClickListener {
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${firstNumberPicker.value}${secondNumberPicker.value}${thirdNumberPicker.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                // Access authorised!
                //TODO -> open the journal page, but first we should create the journal page(view)
                //startActivity()
            } else {
                // Access Failed!
                //TODO -> error pops up
                AlertDialog.Builder(this)
                    .setTitle("FAILED!!!")
                    .setMessage("WRONG CODE!")
                    .setPositiveButton("Confirm") { _, _ -> }
                    .create()
                    .show()
            }
        }

    }
}