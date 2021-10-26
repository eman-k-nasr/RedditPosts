package com.example.redditposts.framework.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.redditposts.R

@ExperimentalCoilApi
@Composable
fun CoilImage(url: String,isVideo:Boolean) {
    Box(contentAlignment = Alignment.Center) {
        val painter = rememberImagePainter(
            data = url,
            builder = {
                placeholder(R.drawable.ic_default_48)
                crossfade(1000)
            }
        )
        val iconPainter = painterResource(R.drawable.ic_play_24)

        Image(
            painter = painter,
            contentScale = FillWidth,
            contentDescription = "post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        if(isVideo){
            Image(
                painter = iconPainter,
                contentDescription = "play icon",
                modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}