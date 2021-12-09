package com.example.taintedscore.room

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GamesApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { GamesDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { GamesRepository(database.gamesDao()) }
}