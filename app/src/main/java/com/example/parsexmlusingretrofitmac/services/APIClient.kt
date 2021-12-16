package com.example.parsexmlusingretrofitmac.services

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl("http://ax.itunes.apple.com")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        return retrofit
    }
}