package com.example.taintedscore.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.room.GamesDatabase
import com.example.taintedscore.room.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResponseViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<SearchResponseData>>
    private val repository: GamesRepository

     init {
        val gamesDao = GamesDatabase.getDatabase(application).gamesDao()
        repository = GamesRepository(gamesDao)
        readAllData = repository.readAllData
    }

    fun addGame(searchResponseData: SearchResponseData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGame(searchResponseData)
        }
    }
}