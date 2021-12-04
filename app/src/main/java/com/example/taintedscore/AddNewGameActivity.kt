package com.example.taintedscore

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.taintedscore.models.ExtViewModel

class AddNewGameActivity : AppCompatActivity() {

    val minCharLength: Int = 2
    private val extViewModel = viewModels<ExtViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_game)
        val searchGame = findViewById<EditText>(R.id.searchBGG)
        val kshowTextResult = findViewById<TextView>(R.id.textView2)

        extViewModel.value.searchLiveData.observe(this) {
            kshowTextResult.append(this.toString())
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