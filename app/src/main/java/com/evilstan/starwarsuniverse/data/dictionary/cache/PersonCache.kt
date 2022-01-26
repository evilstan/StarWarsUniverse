package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PersonCache(
    @PrimaryKey
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: ArrayList<FilmCache>,

    ) {

    private fun filmsToString(): String {
        var saga = ""

        for (film in films) {
            saga += "$film, "
        }
        return saga
    }

    fun toList() = listOf(
        "Name: $name",
        "Height: $height",
        "Weight: $mass",
        "Hair color: $hair_color",
        "Skin color: $skin_color",
        "Eye color: $eye_color",
        "Birth year: $birth_year",
        "Gender: $gender",
        "Home world: $homeworld",
        "Films: ${filmsToString()}"
    )
}
