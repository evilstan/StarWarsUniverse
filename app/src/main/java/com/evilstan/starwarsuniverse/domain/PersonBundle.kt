package com.evilstan.starwarsuniverse.domain

import android.os.Bundle
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import java.util.ArrayList

class PersonBundle {

    fun makeBundle(personCache: PersonCache):Bundle{
        val bundle = Bundle()
        bundle.putString("name", personCache.name)
        bundle.putString("height", personCache.height)
        bundle.putString("mass", personCache.mass)
        bundle.putString("hair_color", personCache.hair_color)
        bundle.putString("skin_color", personCache.skin_color)
        bundle.putString("eye_color", personCache.eye_color)
        bundle.putString("birth_year", personCache.birth_year)
        bundle.putString("gender", personCache.gender)
        bundle.putStringArrayList("films", personCache.films as ArrayList<String>)
        bundle.putBoolean("favorite",personCache.favorite)
        return bundle
    }

    fun makePerson(bundle: Bundle) = PersonCache(
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