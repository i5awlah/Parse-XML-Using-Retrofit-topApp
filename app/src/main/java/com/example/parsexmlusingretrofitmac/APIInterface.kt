package com.example.parsexmlusingretrofitmac

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface APIInterface {
    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit={number}/xml")
    fun feed(@Path("number") number: Int): Call<RssFeed?>?

}