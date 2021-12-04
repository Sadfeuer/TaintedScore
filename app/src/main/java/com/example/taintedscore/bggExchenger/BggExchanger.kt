package com.example.taintedscore.bggExchenger

import com.example.taintedscore.data.SearchResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request


private const val BASE_URL = "https://www.boardgamegeek.com/xmlapi2/"
private const val SEARCH = "https://www.boardgamegeek.com/xmlapi/search?search="
private const val BASE_HOTNESS_URL = "https://www.boardgamegeek.com/xmlapi2/hot?parameter"

class BggExchanger(private val okHttpClient: OkHttpClient) {

    suspend fun search(query: String): Result<List<SearchResponseData>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val request = Request.Builder()
                    .url(SEARCH + query)
                    .build()
                val inputStream = okHttpClient.newCall(request)
                    .execute()
                    .body.let {
                        it!!.byteStream()
                    }
                XmlPullParserHandler().parse(inputStream)
            }
        }
    }
}

