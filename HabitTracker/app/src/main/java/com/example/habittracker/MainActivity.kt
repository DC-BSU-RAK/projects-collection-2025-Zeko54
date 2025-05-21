package com.example.habittracker

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.content.Intent
import androidx.core.graphics.drawable.toDrawable


class MainActivity : AppCompatActivity() {

    private lateinit var habitAdapter: HabitAdapter
    private lateinit var habitsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Window insets for edge-to-edge layout (keep your existing code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Find the RecyclerView
        habitsRecyclerView = findViewById(R.id.habitsRecyclerView)
        habitsRecyclerView.layoutManager = LinearLayoutManager(this) // Vertical scrolling

        // 2. Create a list of default habits
        val defaultHabits = mutableListOf(
            Habit("Read for 20 minutes", 5 , "Do this daily", "May 15, 2025"),
            Habit("Drink 8 glasses of water", 3 , "Do this once a week", "May 2, 2025"),
            Habit("Exercise daily", 2 , "Do this thrice a week", "May 1, 2025"),
            Habit("Meditate for 10 minutes", 4 , "Do this daily", "May 2, 2025"),
            Habit("Wake up before 7 AM", 1 , "Do this once a week", "May 6, 2025"),
            Habit("Avoid sugary snacks", 7 , "Do this daily", "May 1, 2025"),
            Habit("Practice coding", 6 , "Do this daily", "May 1, 2025"),
            Habit("Go to bed before 11 PM", 2 , "Do this daily", "May 1, 2025"),
            Habit("Take a walk outside", 4 , "Do this daily", "May 1, 2025"),
            Habit("Write a journal entry", 5 , "Do this daily", "May 1, 2025"),
            Habit("Eat at least one fruit", 3 , "Do this daily", "May 1, 2025")
        )

        // 3. Set up the adapter
        habitAdapter = HabitAdapter(defaultHabits)
        habitsRecyclerView.adapter = habitAdapter

        // 4. Plus button logic for showing the add habit dialog
        val addButton = findViewById<ImageButton>(R.id.addButton)
        addButton.setOnClickListener {
            // Inflate dialog layout
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_habit, null)
            val editHabitName = dialogView.findViewById<EditText>(R.id.editHabitName)
            val btnAddHabit = dialogView.findViewById<Button>(R.id.btnAddHabit)
            val closeDialog = dialogView.findViewById<ImageView>(R.id.closeDialog)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create()

            // Transparent background so the corners look good
            dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

            // Close button logic
            closeDialog.setOnClickListener {
                dialog.dismiss()
            }

            // Add habit button logic
            btnAddHabit.setOnClickListener {
                val newHabitName = editHabitName.text.toString().trim()
                if (newHabitName.isNotEmpty()) {
                    habitAdapter.addHabit(Habit(newHabitName, 0))
                    habitsRecyclerView.scrollToPosition(0)
                    dialog.dismiss()
                } else {
                    editHabitName.error = "Enter a habit name"
                }
            }

            dialog.show()

            // Optional: Control dialog width if it appears too wide
            dialog.window?.setLayout(
                (resources.displayMetrics.widthPixels * 0.85).toInt(),
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        // 5. Navigation Drawer setup
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val menuButton = findViewById<ImageButton>(R.id.menuButton)

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }


        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_motivation -> {
                    startActivity(Intent(this, Motivation::class.java))
                }
                R.id.menu_settings -> {
                    startActivity(Intent(this, Settings::class.java))
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}
