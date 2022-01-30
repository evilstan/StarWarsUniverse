package com.evilstan.starwarsuniverse.domain.mapper

import com.evilstan.starwarsuniverse.data.core.CloudController
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.domain.cache.PersonCache
import com.evilstan.starwarsuniverse.domain.callback.FilmCallback
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
        getFilms(personCloud.films)
    )

    private fun getFilms(urls: ArrayList<String>): ArrayList<String> {
        val films = mutableListOf<String>()
        val cloudController = CloudController()
        val callBack = FilmCallback()
        val filmMapper = FilmMapper()
        runBlocking {

            val job = launch {
                for (url in urls) {
                    cloudController.getFilm(url, callBack)
                    if (callBack.success) {
                        films.add(filmMapper.map(callBack.filmCloud).toString())
                    }
                }
            }
            job.join()
        }
        return films as ArrayList<String>
    }
}