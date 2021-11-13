package com.example.redditposts.framework.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import com.example.redditposts.business.entities.state.UiState
import com.example.redditposts.framework.components.CircularProgressBar
import com.example.redditposts.framework.components.ErrorState
import com.example.redditposts.framework.components.RedditPostRecyclerView
import com.example.redditposts.framework.components.SearchBar
import com.example.redditposts.framework.features.home.viewmodel.RedditPostsViewModel

@ExperimentalUnitApi
@Composable
fun RedditPostsView(
    navController: NavHostController,
    viewmodel: RedditPostsViewModel
){
    Column() {
        SearchBar(
            query = viewmodel.query.value,
            onQueryChanged = viewmodel::onQueryChanged,
            onExecuteSearch = viewmodel::onTriggerEvent
        )
        HandleUiState(navController,viewmodel)
    }
}

@ExperimentalUnitApi
@Composable
private fun HandleUiState(
    navController: NavHostController,
    viewmodel: RedditPostsViewModel
) {
    val response = viewmodel.redditPostsUiState.value
    val page = viewmodel.page.value

    when (response) {
        is UiState.Loading -> CircularProgressBar(
            isLoading = response.isLoading
        )
        is UiState.Success -> RedditPostRecyclerView(
            navController = navController,
            list = response.data,
            page = page,
            onChangeScrollPosition = viewmodel::onChangeScrollPosition,
            onTriggerEvent = viewmodel::onTriggerEvent
        )
        is UiState.Error -> ErrorState(
            error = response.msg
        )
    }
}