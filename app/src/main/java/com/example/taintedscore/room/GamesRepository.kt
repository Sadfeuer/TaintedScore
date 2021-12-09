package com.example.taintedscore.room

import androidx.annotation.WorkerThread
import com.example.taintedscore.data.SearchResponseData
import kotlinx.coroutines.flow.Flow

class GamesRepository(private val gamesDao:GamesDao) {
    val readAllData: Flow<List<SearchResponseData>> = gamesDao.readAllData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(searchResponseData: SearchResponseData){
        gamesDao.insert(searchResponseData)
    }
}