package com.evilstan.starwarsuniverse.data.core

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.data.dictionary.PeopleCloud
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback

class CloudController {

    private var api: StarWarsApi = NetModule().service(StarWarsApi::class.java)

/*    suspend fun search(search: String, callback: Callback<PeopleCloud>) {
        val call: Call<PeopleCloud> = api.search(search)
        delay(1000L)
        call.enqueue(callback)
    }

    fun getPerson(id: Int, callback: Callback<PersonCloud>) {
        val call: Call<PersonCloud> = api.getPersonByIndex(id)
        call.enqueue(callback)
    }

    fun getFilm(url: String, callback: Callback<FilmCloud>) {
        val call: Call<FilmCloud> = api.getFilm(url)
        call.enqueue(callback)
    }*/
}