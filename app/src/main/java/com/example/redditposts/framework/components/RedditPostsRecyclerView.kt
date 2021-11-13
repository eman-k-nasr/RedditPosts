package com.example.redditposts.framework.components

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.redditposts.business.entities.enums.RedditPostsEvent
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.business.utils.Constants
import com.example.redditposts.framework.navigation.Screen
import com.google.gson.Gson

@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun RedditPostRecyclerView(
    navController: NavHostController,
    list: List<Post>,
    page: Int,
    onChangeScrollPosition: (Int) -> Unit,
    onTriggerEvent: (RedditPostsEvent) -> Unit
) {
    fun navigateToDetailsView(post: Post) {
        val json = Uri.encode(Gson().toJson(post))
        navController.navigate(Screen.DetailsScreen.withArgs(json))
    }
    LazyColumn {
        itemsIndexed(items = list) { index, post ->
            onChangeScrollPosition(index)
            if ((index + 1) >= (page * Constants.PAGE_SIZE)) {
                onTriggerEvent(RedditPostsEvent.NextPageEvent)
            }
            RedditPostCard(post = post, onClick = {
                navigateToDetailsView(post)
            })
        }
    }
}