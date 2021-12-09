package com.example.taintedscore.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taintedscore.data.SearchResponseData
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(searchResponseData: SearchResponseData)

    @Query("SELECT * FROM received_data ORDER by name ASC")
    fun readAllData(): Flow<List<SearchResponseData>>

    @Query("DELETE FROM received_data")
    fun delete()

}