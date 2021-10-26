package com.example.redditposts.framework.features.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.business.repository.abstraction.RedditPostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RedditPostsViewModel @Inject constructor(private val repo: RedditPostsRepository) :
    ViewModel() {
    var redditPostsUiState: MutableState<UiState<List<Post>>> =
        mutableStateOf(UiState.Empty)

    fun getRedditPostsRepository(type: String, limit: Int, after: String) {
        redditPostsUiState.value = UiState.Loading(true)
        viewModelScope.launch {
            try {
                val response = repo.getRedditPosts(type, limit, after)
                redditPostsUiState.value = UiState.Loading(false)
                redditPostsUiState.value = UiState.Success(response.data.posts)
            } catch (ex: Exception) {
                redditPostsUiState.value = UiState.Loading(false)
                redditPostsUiState.value =
                    UiState.Error(ex.message ?: "some error..")
            }
        }
    }

    fun searchRedditPostsRepository(query: String, limit: Int, after: String) {
        redditPostsUiState.value = UiState.Loading(true)
        viewModelScope.launch {
            try {
                val response = repo.searchRedditPosts(query, limit, after)
                redditPostsUiState.value = UiState.Loading(false)
                redditPostsUiState.value = UiState.Success(response.data.posts)
            } catch (ex: Exception) {
                redditPostsUiState.value = UiState.Loading(false)
                redditPostsUiState.value =
                    UiState.Error(ex.message ?: "some error..")
            }
        }
    }
}