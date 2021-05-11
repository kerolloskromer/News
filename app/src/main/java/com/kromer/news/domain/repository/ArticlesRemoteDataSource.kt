package com.kromer.news.domain.repository

import com.kromer.news.domain.model.Article

/**
 * Main entry point for accessing articles remote data.
 */
interface ArticlesRemoteDataSource {
    suspend fun getArticles(q: String, pageSize: Int, page: Int): List<Article>
}