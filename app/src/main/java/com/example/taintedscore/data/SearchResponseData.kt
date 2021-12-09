package com.example.taintedscore.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taintedscore.R

@Entity(tableName = "received_data")
class SearchResponseData {


    @PrimaryKey
    var objectid: String = "0"
    var yearpublished: Int = 0
    var name: String = "Wrong name"
    var totalWins: Int = 0
    var totalLoses: Int = 0
    var totalGames: Int = totalLoses + totalWins
    var totalWinrate: Int = 0

    fun setWinrate() {
        if (totalGames >= 1) {
            totalWinrate = totalWins / totalGames
        }
    }

    override fun toString(): String {
        return " Id = $objectid\n Name = $name\n Year Published = $yearpublished"
    }

    fun toScoreData(): ScoreData {
        val scoreData = ScoreData(
            R.drawable.pic6107853, name, totalWins.toString(), totalGames
        )
        return scoreData
    }
}

