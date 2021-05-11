package com.kromer.news.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.kromer.news.domain.model.Article
import com.kromer.news.domain.repository.ArticlesRemoteDataSource
import com.kromer.news.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articlesRemoteDataSource: ArticlesRemoteDataSource
) : ArticlesRepository {

    override fun getArticles(q: String, pageSize: Int): LiveData<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlesPagingSource(articlesRemoteDataSource, q) }
        ).liveData
}