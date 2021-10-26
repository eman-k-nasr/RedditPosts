package com.example.redditposts.business.entities.state

sealed class UiState<out T>{
    object Empty : UiState<Nothing>()
    data class Loading(val isLoading: Boolean) : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val msg: String) : UiState<Nothing>()
}
