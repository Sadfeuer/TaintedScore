package com.example.taintedscore.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taintedscore.data.SearchResponseData

@Database(version = 1, entities = [SearchResponseData::class])
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object {
        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getDatabase(context: Context): GamesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "games_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}