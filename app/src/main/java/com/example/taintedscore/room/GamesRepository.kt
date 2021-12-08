package com.example.taintedscore.room

import androidx.lifecycle.LiveData
import com.example.taintedscore.data.SearchResponseData

class GamesRepository(private val gamesDao:GamesDao) {
    val readAllData:LiveData<List<SearchResponseData>> = gamesDao.readAllData()
  suspend fun addGame(searchResponseData: SearchResponseData){
        gamesDao.addGameData(searchResponseData)
    }
}