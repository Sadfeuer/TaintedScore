package com.example.taintedscore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taintedscore.bggExchenger.BggExchanger
import com.example.taintedscore.bggExchenger.ClientHolder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExtViewModel : ViewModel() {
    private val delayTime: Long = 5000
    private val bggExchanger = BggExchanger(ClientHolder.okHttpClient)
    private var searchJob: Job? = null

    private val protSearchLiveData = MutableLiveData<List<Any>>()
    val searchLiveData: LiveData<List<Any>> get() = protSearchLiveData

    private val protErrorLiveData = MutableLiveData<String>()
    val errorLiveData: MutableLiveData<String> get() = protErrorLiveData

    val exceptHandler = CoroutineExceptionHandler { _, t ->
        errorLiveData.postValue(t.toString())
    }

    override fun onCleared() {
        super.onCleared()
        searchJob = null
    }

    fun startSearch(text: CharSequence) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(exceptHandler) {
            delay(delayTime)
            val gamesResponse = bggExchanger.search(text.toString())
            if (gamesResponse.isSuccess) {
                gamesResponse.getOrNull()?.let {
                    protSearchLiveData.postValue(listOf(it))
                } ?: run {
                    protErrorLiveData
                        .postValue(gamesResponse.exceptionOrNull()?.message ?: "DATA ERROR")
                }
            }
        }
    }
}