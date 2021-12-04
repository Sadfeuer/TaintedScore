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
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if
                            (tagname.equals("boardgame", ignoreCase = true)) {
                        game?.let { games.add(it) }
                        //game?.objectid = parser.getAttributeValue(null, "objectid")

                        //если раскомментить строку выше, то объекты не добавляются в лист
                        //в других случаях я не могу достать из нестед тега ай-ди, который мне очень нужен
                        //так как по логике мы уже уходим от тега "боардгейм" и следующие две строки кода не работают
                        // пример ХМЛ оставлю ниже кода, если не понятно о чём речь

                    } else if (tagname.equals("boardgame", ignoreCase = true)) {
                        game?.objectid = parser.getAttributeValue(null, "objectid")
                    } else if (tagname.equals("name", ignoreCase = true)) {
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

/* <boardgames termsofuse="https://boardgamegeek.com/xmlapi/termsofuse">
<boardgame objectid="340325">
<name primary="true">Vagrantsong</name>
<yearpublished>2022</yearpublished>
</boardgame>
</boardgames>*/