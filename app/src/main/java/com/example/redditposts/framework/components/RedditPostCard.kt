package com.example.redditposts.framework.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.redditposts.R
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.framework.utils.CoilImage


@ExperimentalUnitApi
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
            Row(

            ) {
                Text(
                    text = "Posted by ",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = TextUnit(
                            12.0f, TextUnitType.Sp
                        )
                    )
                )
                Text(
                    text = post.postData.author,
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = TextUnit(
                            14.0f, TextUnitType.Sp
                        ),
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = post.postData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                style = TextStyle(
                    fontSize = TextUnit(
                        16.0f, TextUnitType.Sp
                    ),
                    fontWeight = FontWeight.Bold
                )
            )

            Row(

            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_up_24),
                    contentDescription = "ups",
                    modifier = Modifier.padding(8.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "${post.postData.ups}",
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    ),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = TextUnit(
                            14.0f, TextUnitType.Sp
                        )
                    )
                )
            }

            if(post.postData.thumbnail.startsWith("https://")){
                CoilImage(post.postData.thumbnail, post.postData.isVideo)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_comment_24),
                    contentDescription = "coments",
                    modifier = Modifier.padding(8.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "${post.postData.numComments} comments",
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    ),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = TextUnit(
                            14.0f, TextUnitType.Sp
                        )
                    )

                )
            }
        }
    }
}