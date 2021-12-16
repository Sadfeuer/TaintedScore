package com.example.taintedscore.data

import com.example.taintedscore.R

class ExampleData {
    val item = ScoreData(R.drawable.pic6107853, "Ankh", "66%", 3)
    val item2 = ScoreData(R.drawable.pic6306248, "Horrified", "100%", 4)
    val item3 = ScoreData(R.drawable.pic5666597, "Dune: Imperium", "0%", 1)
    val item4 = ScoreData(R.drawable.pic6230642, "Unfathomable", "33%", 6)
    val item5 = ScoreData(R.drawable.pic1854807, "BattleLore", "50%", 2)
    val item6 = ScoreData(R.drawable.pic3979527, "Kemet", "100", 1)
    fun generateExampleList(): MutableList<ScoreData> {
        return listOf(item, item2, item3, item4, item5, item6).toMutableList()
    }

    var winrate = 55
    var plays = 245
}