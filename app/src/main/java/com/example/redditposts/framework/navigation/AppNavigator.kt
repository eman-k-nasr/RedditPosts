package com.example.redditposts.framework.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.redditposts.business.entities.response.Post
import com.example.redditposts.framework.components.RedditPostDetailsView
import com.example.redditposts.framework.features.home.viewmodel.RedditPostsViewModel
import com.example.redditposts.framework.navigation.screens.RedditPostsView
import com.example.redditposts.framework.utils.Constants.Companion.POST_ARGUMENT_KEY

@ExperimentalUnitApi
@Composable
fun AppNavigator(
    viewmodel: RedditPostsViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            RedditPostsView(navController = navController, viewmodel = viewmodel)
        }
        composable(
            route = "${Screen.DetailsScreen.route}/{$POST_ARGUMENT_KEY}",
            arguments = listOf(
                navArgument(POST_ARGUMENT_KEY) {
                    type = AssetParamType()
                }
            )
        ) {
            val post = it.arguments?.getParcelable<Post>(POST_ARGUMENT_KEY)
            RedditPostDetailsView(post = post)
        }
    }

}
