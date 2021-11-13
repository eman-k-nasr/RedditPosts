package com.example.redditposts.framework.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.example.redditposts.business.entities.response.Post
import com.google.gson.Gson

class AssetParamType : NavType<Post>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Post? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Post {
        return Gson().fromJson(value, Post::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Post) {
        bundle.putParcelable(key, value)
    }
}