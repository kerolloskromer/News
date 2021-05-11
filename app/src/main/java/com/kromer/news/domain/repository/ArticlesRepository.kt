package com.kromer.news.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.kromer.news.domain.model.Article

/**
 * Interface to the data layer.
 */
interface ArticlesRepository {
    fun getArticles(q: String, pageSize: Int): LiveData<PagingData<Article>>
}