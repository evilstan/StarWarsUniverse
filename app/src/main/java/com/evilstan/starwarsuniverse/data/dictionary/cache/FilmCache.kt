package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmCache(
    @PrimaryKey
    val title:String,
    val episode:Int
)
