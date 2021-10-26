package com.example.redditposts.framework.utils

import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.redditposts.R

@ExperimentalCoilApi
@Composable
fun CoilImage(url: String) {
    Box(contentAlignment = Alignment.Center) {
        val pointer = rememberImagePainter(
            data = url,
            builder = {
                placeholder(R.drawable.ic_default_48)
                crossfade(1000)
            }
        )
        Image(
            painter = pointer,
            contentScale = FillWidth,
            contentDescription = "post image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}