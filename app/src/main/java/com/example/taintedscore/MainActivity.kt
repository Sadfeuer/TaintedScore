package com.example.taintedscore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taintedscore.data.ExampleData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val openGamesList = findViewById<Button>(R.id.gamesListButton)
        val openPlayerStats = findViewById<Button>(R.id.playerStatsButton)
        val totalPlaysStat = findViewById<TextView>(R.id.textViewTotalPlays)
        val totalWinrateStat = findViewById<TextView>(R.id.textViewWinrate)

        openGamesList?.setOnClickListener {
            val toGamesList = Intent(
                this,
                GameListActivity::class.java
            )
            startActivity(toGamesList)
        }
        totalPlaysStat?.append(" " + ExampleData().winrate.toString())
        totalWinrateStat?.append(" " + ExampleData().plays.toString())

        openPlayerStats?.setOnClickListener {
            val toPlayerStats = Intent(
                this,
                PlayerStatsActivity::class.java
            )
            startActivity(toPlayerStats)
        }

    }
}