package com.example.redditposts.business.repository.abstraction

import com.example.redditposts.business.entities.response.RedditResponse

interface RedditPostsRepository {
    suspend fun getRedditPosts(type: String, limit: Int, after: String): RedditResponse
    suspend fun searchRedditPosts(query: String, limit: Int, after: String): RedditResponse
}