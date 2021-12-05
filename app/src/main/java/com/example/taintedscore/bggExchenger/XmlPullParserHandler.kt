package com.example.taintedscore.bggExchenger

import com.example.taintedscore.data.SearchResponseData
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlPullParserHandler {
    private val games = ArrayList<SearchResponseData>()
    private var game: SearchResponseData? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): List<SearchResponseData> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagname.equals("boardgame", ignoreCase = true)) {
                        game = SearchResponseData()
                        game?.let { games.add(it) }
                        game?.objectid = parser.getAttributeValue(null, "objectid")
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if
                                                     (tagname.equals("name", ignoreCase = true)) {
                        game?.name = text
                    } else if (tagname.equals("yearpublished", ignoreCase = true)) {
                        game?.yearpublished = Integer.parseInt(text)
                    }
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return games
    }
}
