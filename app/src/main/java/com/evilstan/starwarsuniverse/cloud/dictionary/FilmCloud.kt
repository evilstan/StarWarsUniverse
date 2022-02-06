package com.evilstan.starwarsuniverse.cloud.dictionary

import com.google.gson.annotations.SerializedName

interface FilmCloud {

    class Base
        (
        @SerializedName("title")
        val title: String = "",
        @SerializedName("episode_id")
        val episodeId: Int = 0
    ) : FilmCloud {

        override fun toString() = "Star Wars: Episode ${toRoman(episodeId)}: $title"

        private fun toRoman(episode: Int) =
            when (episode) {
                1 -> "I"
                2 -> "II"
                3 -> "III"
                4 -> "IV"
                5 -> "V"
                6 -> "VI"
                else -> {
                    ""
                }
            }
    }
}

