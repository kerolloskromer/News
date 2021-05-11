package com.kromer.news.presentation.ui.main.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kromer.news.domain.interactor.GetArticlesInteractor
import com.kromer.news.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesInteractor: GetArticlesInteractor
) : BaseViewModel() {

    private val currentQuery = MutableLiveData<String>()

    fun getArticles(pageSize: Int) = currentQuery.switchMap { queryString ->
        getArticlesInteractor.getArticles(queryString, pageSize).cachedIn(viewModelScope)
    }

    fun search(query: String) {
        currentQuery.value = query
    }
}