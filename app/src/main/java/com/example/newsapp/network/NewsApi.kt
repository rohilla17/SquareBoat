package com.example.newsapp.network

import com.example.newsapp.model.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getHeadlines(@Query("country") country: String,
                     @Query("category") category: String,
                     @Query("apiKey") apiKey : String) : Observable<News>

}