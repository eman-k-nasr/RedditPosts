package com.example.redditposts.framework.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.redditposts.R
import com.example.redditposts.business.entities.enums.RedditPostsEvent
import com.example.redditposts.business.entities.enums.RedditPostsEvent.NewSearchEvent

@Composable
fun SearchBar(
    query : String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: (RedditPostsEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = {
            Text("Search")
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search_24),
                contentDescription = "search",
                tint = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onExecuteSearch(NewSearchEvent)
                focusManager.clearFocus()
            }
        )
    )
}