package com.example.habittracker

//created to data class representing a habit which basically is like a modal and holds all information
// regarding each habit.
data class Habit(
    val name: String,
    val streak: Int = 0,
    val goal: String = "Do this daily",
    val startedDate: String = "May 20, 2025"

)
