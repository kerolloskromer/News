package com.kromer.news.data.source.remote

import com.google.gson.annotations.SerializedName
import com.kromer.news.domain.model.Article

data class ArticlesResponse(
    @SerializedName("articles")
    val articles: List<Article>
)