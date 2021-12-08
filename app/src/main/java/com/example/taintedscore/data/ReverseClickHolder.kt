package com.example.taintedscore.data

class ReverseClickHolder() {
    lateinit var neededData: SearchResponseData
    fun pullData(onClickData: SearchResponseData) {
        neededData = onClickData
    }

    fun retriveData(): SearchResponseData {
        return neededData
    }
}