package com.evilstan.starwarsuniverse.cloud.core

import com.evilstan.starwarsuniverse.cloud.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.cloud.dictionary.PersonCloud
import com.evilstan.starwarsuniverse.cloud.dictionary.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun search(@Query("search") search:String): ResponseWrapper<ArrayList<PersonCloud.Base>>

    @GET("{fullUrl}")
    suspend fun getFilm(@Path(value = "fullUrl", encoded = true ) fullUrl:String):ResponseWrapper<FilmCloud>
}