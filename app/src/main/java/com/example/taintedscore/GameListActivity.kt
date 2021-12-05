package com.example.taintedscore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taintedscore.adapter.GamesAdapter
import com.example.taintedscore.data.ExampleData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GameListActivity : AppCompatActivity(), GamesAdapter.OnItemClickListener {

    private var gamesRecycler: RecyclerView? = null
    private var searchGameButton: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        searchGameButton = findViewById(R.id.addButtonReg)
        gamesRecycler = findViewById(R.id.gamesRecyclerView)
        gamesRecycler?.adapter = GamesAdapter(ExampleData().generateExampleList(), this)
        gamesRecycler?.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.HORIZONTAL
            )
        )
        gamesRecycler?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        gamesRecycler?.setHasFixedSize(true)

        searchGameButton?.setOnClickListener {
            val toSearch = Intent(
                this,
                AddNewGameActivity::class.java
            )
            startActivity(toSearch)
        }
    }

    override fun onItemClick(position: Int) {
        val toPropGameActivity = Intent(
            this,
            PropGameActivity::class.java
        )
        startActivity(toPropGameActivity)
    }
}