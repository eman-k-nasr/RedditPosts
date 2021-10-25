package com.example.redditposts.business.datasource.remote.services

import com.example.redditposts.business.entities.response.RedditResponse
import com.example.redditposts.business.utils.Constants.Companion.AFTER
import com.example.redditposts.business.utils.Constants.Companion.LIMIT
import com.example.redditposts.business.utils.Constants.Companion.QUERY
import com.example.redditposts.business.utils.Constants.Companion.TYPE
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditPostsService {
    @GET("top.json")
    suspend fun getRedditPosts(
        @Query(TYPE) type: String,
        @Query(LIMIT) limit: Int,
        @Query(AFTER) after: String
    ): RedditResponse

    @GET("search.json")
    suspend fun searchRedditPosts(
        @Query(QUERY) query: String,
        @Query(LIMIT) limit: Int,
        @Query(AFTER) after: String
    ): RedditResponse
}