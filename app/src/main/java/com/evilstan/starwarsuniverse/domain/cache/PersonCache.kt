package com.evilstan.starwarsuniverse.domain.cache

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
    var films: ArrayList<String>,
    var favorite: Boolean
) : Parcelable

