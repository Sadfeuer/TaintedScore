package com.example.taintedscore.data

class ReverseClickHolder(srchData: SearchResponseData) {
    var innerSrchData: SearchResponseData = srchData

    fun retriveData(): SearchResponseData {
        return innerSrchData
    }
}