package com.example.habittracker

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class habitdetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val habitGoal = intent.getStringExtra("habit_goal") ?: ""
        val habitStarted = intent.getStringExtra("habit_started") ?: ""
        val habitStreak = intent.getIntExtra("habit_streak", 0)
        val habitName = intent.getStringExtra("habit_name") ?: ""

        findViewById<TextView>(R.id.detailHabitName).text = habitName
        findViewById<TextView>(R.id.detailHabitGoal).text = "Goal: $habitGoal"
        findViewById<TextView>(R.id.detailHabitStarted).text = "Started: $habitStarted"
        findViewById<TextView>(R.id.detailHabitStreak).text = "Streak: $habitStreak days"
        findViewById<TextView>(R.id.detailCompletionHistory).text =
            "Completion History: You completed this habit $habitStreak times this month."

        val btnMarkDone = findViewById<Button>(R.id.btnMarkDone)
        btnMarkDone.setOnClickListener {
            Toast.makeText(this, "Feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        val btnEditHabit = findViewById<Button>(R.id.btnEditHabit)
        btnEditHabit.setOnClickListener {
            Toast.makeText(this, "Edit feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        val btnDeleteHabit = findViewById<Button>(R.id.btnDeleteHabit)
        btnDeleteHabit.setOnClickListener {
            Toast.makeText(this, "Delete feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    // Add this to hide the keyboard when touching outside EditText
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus!!.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}
