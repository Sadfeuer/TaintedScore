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
    var totalGames: Int = 0

    override fun toString(): String {
        return " Id = $objectid\n Name = $name\n Year Published = $yearpublished"
    }

    fun toScoreData(): ScoreData {
        val scoreData = ScoreData(
            R.drawable.pic6107853, name, "0"
        )
        return scoreData

    }
}

