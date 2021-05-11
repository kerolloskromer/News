package com.kromer.news.data.source.remote

import com.kromer.news.domain.model.Article
import com.kromer.news.domain.repository.ArticlesRemoteDataSource
import javax.inject.Inject

class ArticlesRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ArticlesApiInterface
) :
    ArticlesRemoteDataSource {
    override suspend fun getArticles(q: String, pageSize: Int, page: Int): List<Article> =
        apiInterface.getArticles(q, pageSize, page).articles
}