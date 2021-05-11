package com.kromer.news.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApiInterface {

    @GET("everything")
    suspend fun getArticles(
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): ArticlesResponse
}