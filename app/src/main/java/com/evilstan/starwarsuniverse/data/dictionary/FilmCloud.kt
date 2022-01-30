package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName

data class FilmCloud(
    @SerializedName("title") val title: String,
    @SerializedName("episode_id") val episodeId: Int,
)

