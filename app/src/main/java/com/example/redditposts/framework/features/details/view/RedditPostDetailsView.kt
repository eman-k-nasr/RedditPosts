package com.example.redditposts.framework.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.redditposts.business.entities.response.Post

@Composable
fun RedditPostDetailsView(post: Post?){
    Column(

    ){
        post?.let{
            Text(
                text = it.postData.title
            )
        }
    }
}