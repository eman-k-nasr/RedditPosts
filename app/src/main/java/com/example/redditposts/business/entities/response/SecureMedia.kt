package com.example.redditposts.business.entities.response

import com.google.gson.annotations.SerializedName

data class SecureMedia(
    @SerializedName("reddit_video") var redditVideo: RedditVideo
)

data class RedditVideo(
    @SerializedName("fallback_url") var fallbackUrl: String,
    @SerializedName("height") var height: Int,
    @SerializedName("width") var width: Int,
    @SerializedName("scrubber_media_url") var scrubberMediaUrl: String,
    @SerializedName("dash_url") var dashUrl: String,
    @SerializedName("duration") var duration: Int,
    @SerializedName("hls_url") var hlsUrl: String,
    @SerializedName("is_gif") var isGif: Boolean,
    @SerializedName("transcoding_status") var transcodingStatus: String

)
