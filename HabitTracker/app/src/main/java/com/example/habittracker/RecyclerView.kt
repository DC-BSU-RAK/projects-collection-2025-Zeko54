package com.example.habittracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(private val habits: MutableList<Habit>) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val habitName: TextView = view.findViewById(R.id.habitName)
        val habitProgress: TextView = view.findViewById(R.id.habitProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.habitName.text = habit.name
        holder.habitProgress.text = "Streak: ${habit.streak} days"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, habitdetail::class.java)
            intent.putExtra("habit_name", habit.name)
            intent.putExtra("habit_streak", habit.streak)
            intent.putExtra("habit_goal", habit.goal)
            intent.putExtra("habit_started", habit.startedDate)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = habits.size

    fun addHabit(habit: Habit) {
        habits.add(0, habit)
        notifyItemInserted(0)
    }
}
