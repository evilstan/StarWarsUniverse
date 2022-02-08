package com.evilstan.starwarsuniverse.cloud.dictionary

import com.evilstan.starwarsuniverse.domain.models.CharacterCache
import com.evilstan.starwarsuniverse.domain.models.CharacterUi
import com.google.gson.annotations.SerializedName

interface PersonCloud {
    fun map(): CharacterUi

    class Base(
        @SerializedName("name")
        val name: String,

        @SerializedName("height")
        val height: String,

        @SerializedName("mass")
        val mass: String,

        @SerializedName("hair_color")
        val hairColor: String,

        @SerializedName("skin_color")
        val skinColor: String,

        @SerializedName("eye_color")
        val eyeColor: String,

        @SerializedName("birth_year")
        val birthYear: String,

        @SerializedName("gender")
        val gender: String,

        @SerializedName("films")
        val films: ArrayList<String>
    ) : PersonCloud {
        override fun map() = CharacterUi(
            name,
            height,
            mass,
            hairColor,
            skinColor,
            eyeColor,
            birthYear,
            gender,
            films,
            false
        )
    }
}