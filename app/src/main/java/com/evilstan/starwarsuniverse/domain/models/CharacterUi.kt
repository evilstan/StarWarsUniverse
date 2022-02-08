package com.evilstan.starwarsuniverse.domain.models

import androidx.room.PrimaryKey

class CharacterUi(
    @PrimaryKey
    override val name: String,
    override val height: String,
    override val mass: String,
    override val hairColor: String,
    override val skinColor: String,
    override val eyeColor: String,
    override val birthYear: String,
    override val gender: String,
    override var films: List<String>,
    override var favorite: Boolean
) : Character