package com.evilstan.starwarsuniverse.data.core

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.data.dictionary.ResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun search(@Query("search") search:String): ResponseWrapper<ArrayList<PersonCloud>>

    @GET("{fullUrl}")
    suspend fun getFilm(@Path(value = "fullUrl", encoded = true ) fullUrl:String):ResponseWrapper<FilmCloud>


}