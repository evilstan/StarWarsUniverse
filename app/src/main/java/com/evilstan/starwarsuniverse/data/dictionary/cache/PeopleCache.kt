package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud

@Entity(tableName = "people")
data class PeopleCache(
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
    val films: ArrayList<FilmCloud>,

    ) {

}