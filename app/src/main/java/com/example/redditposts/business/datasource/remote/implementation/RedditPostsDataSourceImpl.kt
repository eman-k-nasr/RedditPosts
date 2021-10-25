package com.example.redditposts.business.datasource.remote.implementation

import com.example.redditposts.business.datasource.remote.abstraction.RedditPostsDataSource
import com.example.redditposts.business.datasource.remote.services.RedditPostsService

class RedditPostsDataSourceImpl(val redditPostsService: RedditPostsService) :
    RedditPostsDataSource {
    override suspend fun getRedditPosts(type: String, limit: Int, after: String) =
        redditPostsService.getRedditPosts(type, limit, after)

    override suspend fun searchRedditPosts(query: String, limit: Int, after: String) =
        redditPostsService.searchRedditPosts(query, limit, after)

}