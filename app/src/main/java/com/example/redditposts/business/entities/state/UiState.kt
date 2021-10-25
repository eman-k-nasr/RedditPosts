package com.example.redditposts.business.entities.state

sealed class UiState<out T>{
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val msg: String) : UiState<Nothing>()
}
