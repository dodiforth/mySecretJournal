package com.example.mysecretdays

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class JournalActivity : AppCompatActivity() {

    private val journalEditText: EditText by lazy {
        findViewById<EditText>(R.id.journalEditText)
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val journalPreference = getSharedPreferences("journal", Context.MODE_PRIVATE)

        //Bring the context which already has been written
        journalEditText.setText(journalPreference.getString("note", ""))

        //Change & Save
        val runnable = Runnable {
            getSharedPreferences("journal", Context.MODE_PRIVATE).edit {
                putString("note", journalEditText.text.toString())
            }
        }

        journalEditText.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }


    }
}