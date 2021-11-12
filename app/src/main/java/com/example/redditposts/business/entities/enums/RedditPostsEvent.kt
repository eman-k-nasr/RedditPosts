package com.example.redditposts.business.entities.enums

sealed class RedditPostsEvent{
    object NewSearchEvent : RedditPostsEvent()
    object NextPageEvent : RedditPostsEvent()
}
