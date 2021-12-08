package com.example.taintedscore.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taintedscore.data.SearchResponseData

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addGameData(searchResponseData: SearchResponseData)

    @Query("SELECT * FROM received_data ORDER by name ASC")
    fun readAllData():LiveData<List<SearchResponseData>>

    @Delete
    fun delete(searchResponseData: SearchResponseData)


}