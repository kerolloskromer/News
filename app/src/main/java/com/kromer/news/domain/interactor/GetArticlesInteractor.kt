package com.kromer.news.domain.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.kromer.news.domain.model.Article
import com.kromer.news.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticlesInteractor @Inject constructor(
    private val repository: ArticlesRepository
) {
    fun getArticles(q: String, pageSize: Int): LiveData<PagingData<Article>> =
        repository.getArticles(q, pageSize)
}