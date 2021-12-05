package com.example.taintedscore

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.models.ExtViewModel
const val ID: String = "id"
const val NAME: String = "name"
class AddNewGameActivity : AppCompatActivity() {

    val minCharLength: Int = 2
    private val extViewModel = viewModels<ExtViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_game)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val searchGame = findViewById<EditText>(R.id.searchBGG)
        val kshowTextResult = findViewById<TextView>(R.id.textView2)
        var recivedList:List<SearchResponseData>

        extViewModel.value.searchLiveData.observe(this) {
            //SH
            recivedList = it as List<SearchResponseData>
            kshowTextResult.append(this.toString())
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

            /*
            val recDataMap= mutableMapOf<String, String>()

            var assocData=recivedList.associateTo(recDataMap){ (it.name to it.objectid) }
            assocData = HashMap(assocData)

            val adapter = SimpleAdapter(
            //Какие-то проблемы с маппингом. Не принимает ни листы, ни мапы.
                this,
                assocData,
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

             */

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



