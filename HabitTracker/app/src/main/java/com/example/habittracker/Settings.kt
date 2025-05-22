package com.example.habittracker

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Settings : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("UseSwitchCompatOrMaterialCode", "UseKtx")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)

        val darkModeSwitch = findViewById<Switch>(R.id.darkModeSwitch)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        darkModeSwitch.isChecked = isDarkMode
        setDarkMode(isDarkMode)

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setDarkMode(isChecked)
            sharedPreferences.edit().putBoolean("dark_mode", isChecked).apply()
        }

        val notificationsSwitch = findViewById<Switch>(R.id.notificationsSwitch)
        val isNotificationsOn = sharedPreferences.getBoolean("notifications_on", true)
        notificationsSwitch.isChecked = isNotificationsOn

        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("notifications_on", isChecked).apply()
            if (isChecked) {
                Toast.makeText(this, "Notifications ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDarkMode(enabled: Boolean) {
        if (enabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
