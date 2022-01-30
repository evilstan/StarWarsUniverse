package com.evilstan.starwarsuniverse.data.dictionary

import com.google.gson.annotations.SerializedName

data class PersonCloud(
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
)

