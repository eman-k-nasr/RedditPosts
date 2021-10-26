package com.example.redditposts.framework.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.framework.utils.CoilImage


@ExperimentalCoilApi
@Composable
fun RedditPostCard(post: Post, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = post.postData.author,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h5
            )
            Text(
                text = post.postData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.h6
            )
            post.postData.thumbnail?.let {
                CoilImage(it)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ups: ${post.postData.ups}, downs: ${post.postData.downs} ",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "comments: ${post.postData.numComments}",
                    modifier = Modifier.wrapContentWidth(Alignment.End),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}