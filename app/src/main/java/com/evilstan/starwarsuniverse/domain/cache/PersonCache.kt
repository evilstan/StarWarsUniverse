package com.evilstan.starwarsuniverse.domain.cache

import androidx.room.*

@Entity(tableName = "persons")
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
    val films: ArrayList<String>,
    ) {

    var favorite = false

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
        "Films: ${filmsToString()}"
    )
}
