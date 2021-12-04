package com.example.taintedscore.data

class SearchResponseData {
    var objectid: String = "0"
    var yearpublished: Int = 0
    var name: String? = null

    override fun toString(): String {
        return " Id = $objectid\n Name = $name\n Year Published = $yearpublished"
    }

}

