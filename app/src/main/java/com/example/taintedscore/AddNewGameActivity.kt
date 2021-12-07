package com.example.taintedscore

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taintedscore.adapter.SearchAdapter
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.models.ExtViewModel

class AddNewGameActivity : AppCompatActivity() {

    private val minCharLength: Int = 2
    private val extViewModel = viewModels<ExtViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_game)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val searchGame = findViewById<EditText>(R.id.searchBGG)
        val recyclerSearchRes:RecyclerView = findViewById(R.id.recycler_for_search)

        extViewModel.value.searchLiveData.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            val receivedList: List<SearchResponseData> = it.filterIsInstance<SearchResponseData>()

            //вот же байнд
            recyclerSearchRes.apply{
                layoutManager= LinearLayoutManager(this@AddNewGameActivity)
                adapter= SearchAdapter(receivedList)
            }
            recyclerSearchRes.adapter?.notifyDataSetChanged()
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
}


/*
           val recList: List<Any> = it
           val receivedList: List<SearchResponseData> = recList.filterIsInstance<SearchResponseData>()
           var lisSearchResItems = receivedList.map { item ->
               item.toMap()
           }

           val adapter = SimpleAdapter(
               this,
               lisSearchResItems,
               android.R.layout.simple_dropdown_item_1line,
               arrayOf(NAME),
               intArrayOf(android.R.id.text1),
           )

           val textView = findViewById<AutoCompleteTextView>(R.id.text)
           textView.setAdapter(adapter)

           textView.setOnFocusChangeListener { _, bool ->
               if (bool) {
                   textView.showDropDown()
               }
           }*/


