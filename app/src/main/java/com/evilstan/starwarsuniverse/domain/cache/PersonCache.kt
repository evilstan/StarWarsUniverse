package com.evilstan.starwarsuniverse.domain.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonCache(
    @PrimaryKey
    var name: String,
    var height: String,
    var mass: String,
    var hair_color: String,
    var skin_color: String,
    var eye_color: String,
    var birth_year: String,
    var gender: String,
    var films: List<String>,
    var favorite: Boolean
)

