package com.example.taintedscore.bggExchenger

import android.app.Application
import okhttp3.OkHttpClient

class ClientHolder : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        val okHttpClient = OkHttpClient()
    }
}