package com.example.redditposts.framework.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.redditposts.business.entities.response.Post

@Composable
fun RedditPostRecyclerView(list: List<Post>){
    LazyColumn {
        itemsIndexed(items = list){
            index,post -> RedditPostCard(post = post,onClick = {})
        }
    }
}