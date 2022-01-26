package com.evilstan.starwarsuniverse.data.dictionary.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homeworlds")
data class HomeWorldCache(
    @PrimaryKey
    val name:String
)
