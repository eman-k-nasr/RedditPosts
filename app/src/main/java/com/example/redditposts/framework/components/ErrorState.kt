package com.example.redditposts.framework.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.redditposts.R

@ExperimentalUnitApi
@Composable
fun ErrorState(error:String){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (icon,text) = createRefs()
        Icon(
            painter = painterResource(R.drawable.ic_err_48),
            contentDescription = "error",
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            tint = Color.Gray,
        )
        Text(
            text = "some error ocuured please try again \n $error",
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(
                    value = 16.0f,
                    type = TextUnitType.Sp
                ),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.constrainAs(text){
                top.linkTo(icon.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}