package com.example.taintedscore

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taintedscore.adapter.SearchAdapter
import com.example.taintedscore.data.ReverseClickHolder
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.models.ExtViewModel
import com.example.taintedscore.models.SearchResponseViewModel

class AddNewGameActivity : AppCompatActivity(), SearchAdapter.OnItemClickListener {

    private val minCharLength: Int = 2
    private val extViewModel = viewModels<ExtViewModel>()
    lateinit var foundGamesViewModel: SearchResponseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_game)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val searchGame = findViewById<EditText>(R.id.searchBGG)
        val recyclerSearchRes: RecyclerView = findViewById(R.id.recycler_for_search)
        foundGamesViewModel= ViewModelProvider(this)
            .get(SearchResponseViewModel::class.java)
        //вот тут где-то падаем

        extViewModel.value.searchLiveData.observe(this) {
            val recedList: List<SearchResponseData> = it
            //Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

            recyclerSearchRes.layoutManager = LinearLayoutManager(this@AddNewGameActivity)
            recyclerSearchRes.adapter = SearchAdapter(recedList, this)
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
        insertDataToDatabase()
        val toPropGameActivity = Intent(
            this,
            GameListActivity::class.java
        )
        startActivity(toPropGameActivity)
        //ДБ берём отсюда
    }

    private fun insertDataToDatabase() {
        val insertionData = ReverseClickHolder().retriveData()
        foundGamesViewModel.addGame(insertionData)
        Toast.makeText(this, insertionData.toString(), Toast.LENGTH_LONG).show()
    }

}


