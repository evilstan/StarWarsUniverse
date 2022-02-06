package com.evilstan.starwarsuniverse.cloud.dictionary

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseWrapper<T> : Serializable {

    @SerializedName("results")
    val data: T? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("episode_id")
    val episode: Int? = null

}