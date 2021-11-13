package com.example.redditposts.framework.navigation

import com.example.redditposts.framework.utils.Constants.Companion.DETAILS_SCREEN
import com.example.redditposts.framework.utils.Constants.Companion.HOME_SCREEN

sealed class Screen(val route:String){
    object HomeScreen : Screen(HOME_SCREEN)
    object DetailsScreen : Screen(DETAILS_SCREEN)

    fun withArgs(vararg args:String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
