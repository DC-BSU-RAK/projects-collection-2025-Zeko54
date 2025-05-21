package com.example.animoodnancalculator

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class MainActivity : AppCompatActivity() {

    data class EmojiInfo(val emoji: String, val label: String, val description: String)

    // List of emoji info (must match your grid order!)
    private val emojis = listOf(
        EmojiInfo("😢", "Sad / Emotional", "Tragic arcs, heavy themes, heartbreak."),
        EmojiInfo("😂", "Funny / Chill", "Laughs, slice of life, light-hearted fun."),
        EmojiInfo("😡", "Angry / Revenge", "Intense battles, justice, power struggles."),
        EmojiInfo("😎", "Cool / Confident", "OP characters, slick action, untouchable energy."),
        EmojiInfo("🥺", "Cute / Wholesome", "Adorable, feel-good, pure-hearted characters."),
        EmojiInfo("😱", "Shocked / Suspense", "Unexpected twists, thrillers, mystery."),
        EmojiInfo("😍", "Romantic / Crush", "Love stories, shipping wars, tender moments."),
        EmojiInfo("😴", "Chill / Sleepy", "Slow pace, cozy stories, nap vibes."),
        EmojiInfo("🤯", "Mind-blown", "Deep plots, existential themes, mind-bending twists."),
        EmojiInfo("😈", "Mischievous / Dark", "Anti-heroes, morally gray characters, villain vibes."),
        EmojiInfo("🤠", "Adventurous", "Epic journeys, action-packed, thrilling quests."),
        EmojiInfo("🤧", "Sick / Drama", "Sad slice of life, illness arcs, emotional rollercoasters.")
    )

    // Track selected emojis (indices)
    private val selectedEmojiIndices = mutableListOf<Int>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Top bar references
            val emoji1 = findViewById<TextView>(R.id.emoji1)
            val emoji2 = findViewById<TextView>(R.id.emoji2)
            val plusSign = findViewById<TextView>(R.id.plusSign)
            val animeResult = findViewById<TextView>(R.id.animeResult)

            // Map button and icon IDs to their views for all 12 emojis
            val emojiButtons = listOf(
                R.id.emojiBtn1, R.id.emojiBtn2, R.id.emojiBtn3, R.id.emojiBtn4,
                R.id.emojiBtn5, R.id.emojiBtn6, R.id.emojiBtn7, R.id.emojiBtn8,
                R.id.emojiBtn9, R.id.emojiBtn10, R.id.emojiBtn11, R.id.emojiBtn12
            )
            val emojiLabels = listOf(
                R.id.emojiLabel1, R.id.emojiLabel2, R.id.emojiLabel3, R.id.emojiLabel4,
                R.id.emojiLabel5, R.id.emojiLabel6, R.id.emojiLabel7, R.id.emojiLabel8,
                R.id.emojiLabel9, R.id.emojiLabel10, R.id.emojiLabel11, R.id.emojiLabel12
            )
            val infoIcons = listOf(
                R.id.infoIcon1, R.id.infoIcon2, R.id.infoIcon3, R.id.infoIcon4,
                R.id.infoIcon5, R.id.infoIcon6, R.id.infoIcon7, R.id.infoIcon8,
                R.id.infoIcon9, R.id.infoIcon10, R.id.infoIcon11, R.id.infoIcon12
            )

            // Hook up all emoji buttons and info icons
            for (i in 0 until 12) {
                val btn = findViewById<android.widget.FrameLayout>(emojiButtons[i])
                val label = findViewById<TextView>(emojiLabels[i])
                val info = findViewById<ImageView>(infoIcons[i])

                // Emoji selection
                btn.setOnClickListener {
                    if (selectedEmojiIndices.contains(i)) {
                        // Already selected, do nothing (or deselect if you want)
                        Toast.makeText(this, "Already selected!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    if (selectedEmojiIndices.size == 2) {
                        Toast.makeText(this, "Only 2 emojis can be selected!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    selectedEmojiIndices.add(i)
                    label.alpha = 0.6f // Dim selected (visual feedback)
                    btn.alpha = 0.7f

                    // Update EmojiBar
                    if (selectedEmojiIndices.size == 1) {
                        emoji1.text = emojis[i].emoji
                    } else if (selectedEmojiIndices.size == 2) {
                        emoji2.text = emojis[i].emoji
                    }
                }

                // Info icon for meaning
                info.setOnClickListener {
                    val emojiInfo = emojis[i]
                    val message = "${emojiInfo.emoji}  ${emojiInfo.label}\n\n${emojiInfo.description}"

                    androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Mood Meaning")
                        .setMessage(message)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }

            // CLEAR button
            val clearBtn = findViewById<MaterialButton>(R.id.ClearButton)
            clearBtn.setOnClickListener {
                selectedEmojiIndices.clear()
                emoji1.text = ""
                emoji2.text = ""
                animeResult.text = ""
                animeResult.visibility = android.view.View.GONE

                // Restore emoji and plus sign visibility
                emoji1.visibility = android.view.View.VISIBLE
                emoji2.visibility = android.view.View.VISIBLE
                plusSign.visibility = android.view.View.VISIBLE

                // Reset alpha
                for (i in 0 until 12) {
                    val btn = findViewById<android.widget.FrameLayout>(emojiButtons[i])
                    val label = findViewById<TextView>(emojiLabels[i])
                    btn.alpha = 1.0f
                    label.alpha = 1.0f
                }
            }

            // MATCH button (recommendation logic placeholder)
            val matchBtn = findViewById<MaterialButton>(R.id.MatchButton)
            matchBtn.setOnClickListener {
                if (selectedEmojiIndices.size < 2) {
                    Toast.makeText(this, "Select 2 emojis!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val idx1 = selectedEmojiIndices[0]
                val idx2 = selectedEmojiIndices[1]
                val anime = getAnimeRecommendation(idx1, idx2)

                // Hide the emojis and plus sign
                emoji1.visibility = android.view.View.GONE
                emoji2.visibility = android.view.View.GONE
                plusSign.visibility = android.view.View.GONE

                // Show the anime result
                animeResult.text = anime
                animeResult.visibility = android.view.View.VISIBLE
            }


            val infoBtn = findViewById<MaterialButton>(R.id.Infobutton)
            infoBtn.setOnClickListener {
                InstructionsDialogFragment().show(supportFragmentManager, "instructions")
            }
        }

    private val animeMap = mapOf(
        setOf(0, 1) to "Clannad: After Story",
        setOf(0, 2) to "Attack on Titan",
        setOf(0, 3) to "Violet Evergarden",
        setOf(0, 4) to "A Silent Voice",
        setOf(0, 5) to "Anohana: The Flower We Saw That Day",
        setOf(0, 6) to "Your Lie in April",
        setOf(0, 7) to "March Comes in Like a Lion",
        setOf(0, 8) to "Steins;Gate",
        setOf(0, 9) to "Death Parade",
        setOf(0, 10) to "Made in Abyss",
        setOf(0, 11) to "I Want to Eat Your Pancreas",

        setOf(1, 2) to "Gintama",
        setOf(1, 3) to "KonoSuba",
        setOf(1, 4) to "Nichijou",
        setOf(1, 5) to "The Disastrous Life of Saiki K.",
        setOf(1, 6) to "My Love Story!!",
        setOf(1, 7) to "Tanaka-kun is Always Listless",
        setOf(1, 8) to "Grand Blue",
        setOf(1, 9) to "Assassination Classroom",
        setOf(1, 10) to "Haven't You Heard? I'm Sakamoto",
        setOf(1, 11) to "Daily Lives of High School Boys",

        setOf(2, 3) to "Mob Psycho 100",
        setOf(2, 4) to "Tokyo Ghoul",
        setOf(2, 5) to "Akame ga Kill!",
        setOf(2, 6) to "Black Clover",
        setOf(2, 7) to "Kill la Kill",
        setOf(2, 8) to "Psycho-Pass",
        setOf(2, 9) to "Code Geass",
        setOf(2, 10) to "Demon Slayer",
        setOf(2, 11) to "Dororo",

        setOf(3, 4) to "One Punch Man",
        setOf(3, 5) to "Haikyuu!!",
        setOf(3, 6) to "Cowboy Bebop",
        setOf(3, 7) to "Fruits Basket",
        setOf(3, 8) to "No Game No Life",
        setOf(3, 9) to "JoJo's Bizarre Adventure",
        setOf(3, 10) to "Samurai Champloo",
        setOf(3, 11) to "Dr. Stone",

        setOf(4, 5) to "Your Lie in April",
        setOf(4, 6) to "K-On!",
        setOf(4, 7) to "My Dress-Up Darling",
        setOf(4, 8) to "Barakamon",
        setOf(4, 9) to "Kimi ni Todoke",
        setOf(4, 10) to "Horimiya",
        setOf(4, 11) to "Kokoro Connect",

        setOf(5, 6) to "Natsume's Book of Friends",
        setOf(5, 7) to "ReLIFE",
        setOf(5, 8) to "Erased",
        setOf(5, 9) to "Paranoia Agent",
        setOf(5, 10) to "Another",
        setOf(5, 11) to "Angel Beats!",

        setOf(6, 7) to "Kaguya-sama: Love is War",
        setOf(6, 8) to "Ouran High School Host Club",
        setOf(6, 9) to "Fruits Basket",
        setOf(6, 10) to "Nana",
        setOf(6, 11) to "The Pet Girl of Sakurasou",

        setOf(7, 8) to "Laid-Back Camp",
        setOf(7, 9) to "The Melancholy of Haruhi Suzumiya",
        setOf(7, 10) to "Tsurezure Children",
        setOf(7, 11) to "Recovery of an MMO Junkie",

        setOf(8, 9) to "Death Note",
        setOf(8, 10) to "The Promised Neverland",
        setOf(8, 11) to "Parasyte: The Maxim",

        setOf(9, 10) to "Monster",
        setOf(9, 11) to "Ergo Proxy",

        setOf(10, 11) to "One Piece"
    )

    // Simple anime mapping (replace with your real logic)
    private fun getAnimeRecommendation(idx1: Int, idx2: Int): String {
        val pair = setOf(idx1, idx2)
        val anime = animeMap[pair]
        return if (anime != null) {
            "Your anime: $anime"
        } else {
            "No recommendation found for this combo (should never happen!)"
        }
    }

}
