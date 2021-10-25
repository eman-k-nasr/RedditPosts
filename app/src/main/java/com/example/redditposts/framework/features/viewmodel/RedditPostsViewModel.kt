package com.example.redditposts.framework.features.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository
import kotlinx.coroutines.launch


class RedditPostsViewModel(val repo: RedditPostsRepository) : ViewModel() {
    private val _redditPostsUiState: MutableLiveData<UiState<List<Post>>> =
        MutableLiveData()
    val redditPostsUiState: MutableLiveData<UiState<List<Post>>>
        get() = _redditPostsUiState

    private val _searchRedditPostsUiState: MutableLiveData<UiState<List<Post>>> =
        MutableLiveData()
    val searchRedditPostsUiState: MutableLiveData<UiState<List<Post>>>
        get() = _searchRedditPostsUiState

    fun getRedditPostsRepository(type: String, limit: Int, after: String) {
        _redditPostsUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = repo.getRedditPosts(type, limit, after)
                _redditPostsUiState.value = UiState.Success(response.data.posts)
            } catch (ex: Exception) {
                _redditPostsUiState.value =
                    UiState.Error(ex.message ?: "some error..")
            }
        }
    }

    fun searchRedditPostsRepository(query: String, limit: Int, after: String) {
        _searchRedditPostsUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = repo.searchRedditPosts(query, limit, after)
                _searchRedditPostsUiState.value = UiState.Success(response.data.posts)
            } catch (ex: Exception) {
                _searchRedditPostsUiState.value =
                    UiState.Error(ex.message ?: "some error..")
            }
        }
    }
}