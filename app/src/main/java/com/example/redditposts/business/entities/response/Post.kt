package com.example.redditposts.business.entities.response

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("kind") var kind: String,
    @SerializedName("data") var postData: PostData
)
data class PostData(
    @SerializedName("author_fullname") var authorFullname: String,
    @SerializedName("title") var title: String,
    @SerializedName("name") var name: String,
    @SerializedName("total_awards_received") var totalAwardsReceived: Int,
    @SerializedName("thumbnail") var thumbnail: String?,
    @SerializedName("thumbnail_width") var thumbnailWidth: Int,
    @SerializedName("thumbnail_height") var thumbnailHeight : Int,
    @SerializedName("secure_media") var secureMedia: SecureMedia?,
    @SerializedName("score") var score: Int,
    @SerializedName("ups") var ups : Int,
    @SerializedName("downs") var downs : Int,
    @SerializedName("edited") var edited: Boolean,
    @SerializedName("created") var created: Int,
    @SerializedName("likes") var likes: String?,
    @SerializedName("view_count") var viewCount: String?,
    @SerializedName("archived") var archived: Boolean,
    @SerializedName("over_18") var over18: Boolean,
    @SerializedName("id") var id: String,
    @SerializedName("author") var author: String,
    @SerializedName("num_comments") var numComments: Int,
    @SerializedName("url") var url: String?,
    @SerializedName("subreddit_subscribers") var subredditSubscribers: Int,
    @SerializedName("created_utc") var createdUtc: Int,
    @SerializedName("is_video") var isVideo: Boolean
)
