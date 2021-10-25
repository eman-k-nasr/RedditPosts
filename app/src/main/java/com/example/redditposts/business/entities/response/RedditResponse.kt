package com.example.redditposts.business.entities.response

import com.google.gson.annotations.SerializedName

data class RedditResponse(
    @SerializedName("kind") var kind: String,
    @SerializedName("data") var data: Data
)
data class Data(
    @SerializedName("after") var after: String,
    @SerializedName("dist") var dist: Int,
    @SerializedName("modhash") var modhash: String,
    @SerializedName("geo_filter") var geoFilter: String,
    @SerializedName("children") var posts: List<Post>,
    @SerializedName("before") var before: String
)
