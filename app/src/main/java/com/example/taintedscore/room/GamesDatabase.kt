package com.example.taintedscore.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taintedscore.data.SearchResponseData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(version = 1, entities = [SearchResponseData::class])
public abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    private class GamesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { gamesDatabase ->
                scope.launch {
                    var gamesDao = gamesDatabase.gamesDao()

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: GamesDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GamesDatabase {
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