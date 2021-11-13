package com.example.redditposts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import com.example.redditposts.framework.features.home.viewmodel.RedditPostsViewModel
import com.example.redditposts.framework.navigation.AppNavigator
import com.example.redditposts.framework.theme.RedditPostsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val redditPostsViewModel: RedditPostsViewModel by viewModels()

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedditPostsTheme{
                AppNavigator(viewmodel = redditPostsViewModel)
            }
        }
    }
}