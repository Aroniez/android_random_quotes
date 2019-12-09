package com.aroniez.quotes.api

import com.aroniez.quotes.api.callbacks.FortunesCallback
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoints {

    @GET("fortune")
    fun fortunes(): Call<FortunesCallback>

}
