package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName

data class FilmCloud(
    @SerializedName("title") val title: String = "",
    @SerializedName("episode_id") val episodeId: Int = 0,
){
    fun map() = "Star Wars: Episode ${toRoman(episodeId)}: $title"

    private fun toRoman(episode:Int) =
        when (episode) {
            1 -> "I"
            2 -> "II"
            3 -> "III"
            4 -> "IV"
            5 -> "V"
            6 -> "VI"
            else -> {""}
        }
}

