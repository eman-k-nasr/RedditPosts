package com.example.redditposts.framework.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout

@ExperimentalUnitApi
@Composable
fun CircularProgressBar(isLoading: Boolean) {
    if (isLoading) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (progressbar,text) = createRefs()
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressbar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text = "loading",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = TextUnit(
                        value = 16.0f,
                        type = TextUnitType.Sp
                    )
                ),
                modifier = Modifier.constrainAs(text){
                    top.linkTo(progressbar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}