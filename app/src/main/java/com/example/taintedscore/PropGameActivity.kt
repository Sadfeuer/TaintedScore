package com.example.taintedscore

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.taintedscore.data.ExampleData

class PropGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prop_game)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val titleActivity: TextView = findViewById(R.id.title_of_activity)
        val posterActivity: ImageView = findViewById(R.id.imageViewCover)
        posterActivity.setImageResource(R.drawable.pic6107853)
        val addGamesWin = findViewById<Button>(R.id.addWinButton)
        val addGamesLoss = findViewById<Button>(R.id.addLossButton)

        titleActivity.text = "Ankh"
        addGamesWin?.setOnClickListener {
            ExampleData().item.totalgames + 1
        }
        addGamesLoss?.setOnClickListener {
            ExampleData().item.totalgames - 1
        }
    }
}