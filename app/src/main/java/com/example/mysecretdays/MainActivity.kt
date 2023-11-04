package com.example.mysecretdays

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val openLockButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openLockButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePasswordMode = false

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

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("FAILED!!!")
            .setMessage("WRONG CODE!")
            .setPositiveButton("Confirm") { _, _ -> }
            .create()
            .show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNumberPicker
        secondNumberPicker
        thirdNumberPicker

        openLockButton.setOnClickListener {

            if (changePasswordMode) {
                Toast.makeText(
                    this,
                    "During change of password, you can't open it!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${firstNumberPicker.value}${secondNumberPicker.value}${thirdNumberPicker.value}"

            if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                // Access authorised!
                //TODO -> open the journal page, but first we should create the journal page(view)
                startActivity(Intent(this, JournalActivity::class.java))
            } else {
                // Access Failed!
                //TODO -> error pops up
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {
            val passwordPreference = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${firstNumberPicker.value}${secondNumberPicker.value}${thirdNumberPicker.value}"


            if (changePasswordMode) {
                //SAVE the combination
                passwordPreference.edit(true) {
                    putString("password", passwordFromUser)
                }
                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)
            } else {
                //ACTIVATE changePasswordMode :: check if the combination's correct or not (without verification, don't let the user change the comb)
                if (passwordPreference.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "Create new combination", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {
                    // Access Failed!
                    //TODO -> error pops up
                    showErrorAlertDialog()
                }
            }
        }

    }
}