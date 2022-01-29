package com.evilstan.starwarsuniverse.data.dictionary

import com.evilstan.starwarsuniverse.data.dictionary.cache.FilmCache
import com.evilstan.starwarsuniverse.data.dictionary.cache.PersonCache

class PersonMapper {

    fun map(personCloud: PersonCloud) = PersonCache(
        personCloud.name,
        personCloud.height,
        personCloud.mass,
        personCloud.hairColor,
        personCloud.skinColor,
        personCloud.eyeColor,
        personCloud.birthYear,
        personCloud.gender,
        getWorld(personCloud.homeworld),
        getFilms(personCloud.films)
    )


    private fun getWorld(url: String): String {
        return "" //TODO Make parser!
    }

    private fun getFilms(urls: ArrayList<String>): ArrayList<FilmCache> {
        return arrayListOf() //TODO Make parser!
    }
}