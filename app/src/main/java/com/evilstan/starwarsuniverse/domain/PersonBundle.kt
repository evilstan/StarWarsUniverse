package com.evilstan.starwarsuniverse.domain

import android.os.Bundle
import com.evilstan.starwarsuniverse.domain.models.Character
import com.evilstan.starwarsuniverse.domain.models.CharacterUi
import java.util.ArrayList

class PersonBundle {

    fun makeBundle(character: Character):Bundle{
        val bundle = Bundle()
        bundle.putString("name", character.name)
        bundle.putString("height", character.height)
        bundle.putString("mass", character.mass)
        bundle.putString("hair_color", character.hairColor)
        bundle.putString("skin_color", character.skinColor)
        bundle.putString("eye_color", character.eyeColor)
        bundle.putString("birth_year", character.birthYear)
        bundle.putString("gender", character.gender)
        bundle.putStringArrayList("films", character.films as ArrayList<String>)
        bundle.putBoolean("favorite",character.favorite)
        return bundle
    }

    fun makePerson(bundle: Bundle) = CharacterUi(
            bundle.getString("name")!!,
            bundle.getString("height")!!,
            bundle.getString("mass")!!,
            bundle.getString("hair_color")!!,
            bundle.getString("skin_color")!!,
            bundle.getString("eye_color")!!,
            bundle.getString("birth_year")!!,
            bundle.getString("gender")!!,
            bundle.getStringArrayList("films")!!,
            bundle.getBoolean("favorite")
        )
}