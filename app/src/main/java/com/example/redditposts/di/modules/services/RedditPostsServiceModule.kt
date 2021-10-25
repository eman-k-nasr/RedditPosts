package com.example.redditposts.di.modules.services

import com.example.redditposts.business.datasource.remote.abstraction.RedditPostsDataSource
import com.example.redditposts.business.datasource.remote.implementation.RedditPostsDataSourceImpl
import com.example.redditposts.business.datasource.remote.services.RedditPostsService
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository
import com.example.redditposts.business.repository.implementation.RedditPostsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object RedditPostsServiceModule {

    @Singleton
    @Provides
    fun provideRedditPostsService(retrofit: Retrofit) =
        retrofit.create(RedditPostsService::class.java)

    @Singleton
    @Provides
    fun provideRedditPostsDataSource(redditPostsService: RedditPostsService) :
            RedditPostsDataSource= RedditPostsDataSourceImpl(redditPostsService)

    @Singleton
    @Provides
    fun provideRedditPostsRepository(redditPostsDataSource: RedditPostsDataSource) :
            RedditPostsRepository = RedditPostsRepositoryImpl(redditPostsDataSource)

}