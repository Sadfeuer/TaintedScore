package com.example.taintedscore.models

import androidx.lifecycle.*
import com.example.taintedscore.data.SearchResponseData
import com.example.taintedscore.room.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchResponseViewModel(private val repository: GamesRepository) : ViewModel() {
    val readAllData: LiveData<List<SearchResponseData>> = repository.readAllData.asLiveData()

    fun insert(searchResponseData: SearchResponseData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(searchResponseData)
        }
    }

    class SearchResponseViewModelFactory(private val repository: GamesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchResponseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchResponseViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown VM class")
        }

    }
}

