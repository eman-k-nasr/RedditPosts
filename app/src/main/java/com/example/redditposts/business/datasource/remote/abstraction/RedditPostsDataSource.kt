package com.example.redditposts.business.datasource.remote.abstraction

import com.example.redditposts.business.entities.response.RedditResponse

interface RedditPostsDataSource {
    suspend fun getRedditPosts(type: String, limit: Int, after: String): RedditResponse
    suspend fun searchRedditPosts(query: String, limit: Int, after: String): RedditResponse
}