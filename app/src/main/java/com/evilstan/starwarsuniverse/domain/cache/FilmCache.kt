package com.evilstan.starwarsuniverse.domain.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmCache(
    @PrimaryKey
    val title: String,
    val episode: Int
) {
    override fun toString(): String {
        return "Star Wars: Episode ${toRoman()}: $title"
    }

    private fun toRoman() =
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

