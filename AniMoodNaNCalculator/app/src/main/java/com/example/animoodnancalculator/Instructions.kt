package com.example.animoodnancalculator

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Instructions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_instructions)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.instructions_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the cross/close button and finish the activity when clicked
        val closeBtn = findViewById<ImageView>(R.id.closeBtn)
        closeBtn.setOnClickListener {
            finish()
        }
    }
}
