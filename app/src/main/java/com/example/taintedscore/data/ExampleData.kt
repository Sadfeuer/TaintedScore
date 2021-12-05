package com.example.taintedscore.data

import com.example.taintedscore.R

class ExampleData {
    fun generateExampleList(): MutableList<ScoreData> {
        val item = ScoreData(R.drawable.pic6107853, "Ankh", "66%")
        val item2 = ScoreData(R.drawable.pic6306248, "Horrified", "100%")
        val item3 = ScoreData(R.drawable.pic5666597, "Dune: Imperium", "0%")
        val item4= ScoreData(R.drawable.pic6230642, "Unfathomable", "33%")
        return listOf(item, item2, item3, item4).toMutableList()
    }

    var winrate = 55
    var plays = 245
}