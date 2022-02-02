package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseWrapper<T> : Serializable {
    @SerializedName("count")
    val count: Int = 0

    @SerializedName("next")
    val next: String? = null

    @SerializedName("previous")
    val previous: String? = null

    @SerializedName("results")
    val data: T? = null

    @SerializedName("detail")
    val error: String? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("episode_id")
    val episode: Int? = null
}