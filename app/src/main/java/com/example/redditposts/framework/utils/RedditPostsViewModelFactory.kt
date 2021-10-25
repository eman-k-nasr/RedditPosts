package com.example.redditposts.framework.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository
import com.example.redditposts.framework.features.viewmodel.RedditPostsViewModel

class RedditPostsViewModelFactory(val repo: RedditPostsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedditPostsViewModel::class.java)) {
            return RedditPostsViewModel(repo) as T
        }
        throw IllegalArgumentException("viewmodel class is not found")
    }
}