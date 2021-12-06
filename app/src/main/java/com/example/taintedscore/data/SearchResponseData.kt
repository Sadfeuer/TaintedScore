package com.example.taintedscore.data


const val ID: String = "id"
const val NAME: String = "name"

class SearchResponseData {
    var objectid: String = "0"
    var yearpublished: Int = 0
    var name: String = "Wrong name"

    override fun toString(): String {
        return " Id = $objectid\n Name = $name\n Year Published = $yearpublished"
    }
    fun toMap(): Map<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap[ID] = objectid
        hashMap[NAME] = name
        return hashMap
    }
}

