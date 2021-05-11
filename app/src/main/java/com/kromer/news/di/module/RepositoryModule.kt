package com.kromer.news.di.module

import com.kromer.news.data.repository.ArticlesRepositoryImpl
import com.kromer.news.data.source.remote.ArticlesApiInterface
import com.kromer.news.data.source.remote.ArticlesRemoteDataSourceImpl
import com.kromer.news.domain.repository.ArticlesRemoteDataSource
import com.kromer.news.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArticlesRepository(
        articlesRemoteDataSource: ArticlesRemoteDataSource,
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            articlesRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideArticlesRemoteDataSource(
        apiInterface: ArticlesApiInterface
    ): ArticlesRemoteDataSource {
        return ArticlesRemoteDataSourceImpl(
            apiInterface
        )
    }

}
