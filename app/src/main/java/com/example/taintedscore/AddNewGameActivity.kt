package com.example.taintedscore

import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.taintedscore.data.NAME
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

        extViewModel.value.searchLiveData.observe(this) {

            val receivedList: List<SearchResponseData> = it as List<SearchResponseData>
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
            }
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
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



