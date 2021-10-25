package com.example.redditposts.business.repository.implementation

import com.example.redditposts.business.datasource.remote.abstraction.RedditPostsDataSource
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository

class RedditPostsRepositoryImpl(val redditPostsDataSource: RedditPostsDataSource) :
    RedditPostsRepository {
    override suspend fun getRedditPosts(
        type: String,
        limit: Int,
        after: String
    ) = redditPostsDataSource.getRedditPosts(type, limit, after)

    override suspend fun searchRedditPosts(
        query: String,
        limit: Int,
        after: String
    ) = redditPostsDataSource.searchRedditPosts(query, limit, after)
}