package com.example.habittracker

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Motivation : AppCompatActivity() {

    private val quotes = listOf(
        "“Small habits make a big difference.”",
        "“Success is the product of daily habits—not once-in-a-lifetime transformations.”",
        "“You are what you repeatedly do.”",
        "“Don’t break the chain—show up every day.”",
        "“Consistency is more important than intensity.”"
    )
    private var quoteIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quoteText = findViewById<TextView>(R.id.quoteText)
        quoteText.text = quotes[quoteIndex]

        findViewById<Button>(R.id.btnNextQuote).setOnClickListener {
            quoteIndex = (quoteIndex + 1) % quotes.size
            quoteText.text = quotes[quoteIndex]
        }
    }
}

