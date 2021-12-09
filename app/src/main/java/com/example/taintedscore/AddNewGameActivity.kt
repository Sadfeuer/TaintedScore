package com.example.taintedscore

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taintedscore.adapter.SearchAdapter
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.models.ExtViewModel
import com.example.taintedscore.models.SearchResponseViewModel
import com.example.taintedscore.room.GamesApplication


class AddNewGameActivity : AppCompatActivity(), SearchAdapter.OnItemClickListener {

    private val minCharLength: Int = 2
    private val extViewModel = viewModels<ExtViewModel>()
    private val gamesViewModel: SearchResponseViewModel by viewModels {
        SearchResponseViewModel.SearchResponseViewModelFactory((application as GamesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_game)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val searchGame = findViewById<EditText>(R.id.searchBGG)
        val recyclerSearchRes: RecyclerView = findViewById(R.id.recycler_for_search)

        extViewModel.value.searchLiveData.observe(this) {
            val recedList: List<SearchResponseData> = it
            //Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show() test tool
            recyclerSearchRes.layoutManager = LinearLayoutManager(this@AddNewGameActivity)
            recyclerSearchRes.adapter = SearchAdapter(recedList, this)
            recyclerSearchRes.addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerSearchRes.itemAnimator = DefaultItemAnimator();
        }

        extViewModel.value.errorLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        searchGame?.doOnTextChanged { text, start, before, count ->
            if (text != null) {
                if (text.length > minCharLength) {
                    extViewModel.value.startSearch(text)
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        //insertDataToDatabase()

        val toPropGameActivity = Intent(
            this,
            GameListActivity::class.java
        )
        startActivity(toPropGameActivity)
    }
/*
  private fun insertDataToDatabase() {
        val insertionData = ReverseClickHolder().retriveData()
        gamesViewModel.insert(insertionData)
        Toast.makeText(this, insertionData.toString(), Toast.LENGTH_LONG).show()
    }*/

}


