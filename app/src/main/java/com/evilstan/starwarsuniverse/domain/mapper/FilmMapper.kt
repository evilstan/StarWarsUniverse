package com.evilstan.starwarsuniverse.domain.mapper

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud

class FilmMapper {
    fun map(filmCloud: FilmCloud) = "Star Wars: Episode ${toRoman(filmCloud.episodeId)}: ${filmCloud.title}"

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