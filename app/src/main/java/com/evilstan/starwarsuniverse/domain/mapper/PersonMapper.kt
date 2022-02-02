package com.evilstan.starwarsuniverse.domain.mapper

import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.domain.cache.PersonCache

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
        personCloud.films,
        false
    )
}