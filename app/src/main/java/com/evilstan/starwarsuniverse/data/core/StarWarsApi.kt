package com.evilstan.starwarsuniverse.data.core

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.data.dictionary.PeopleCloud
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApi {/*
    @GET("people/")
    fun search(@Query("search") search:String):Call<PeopleCloud>

    @GET("people/{index}")
    fun getPersonByIndex(@Path("index") index:Int):Call<PersonCloud>

    @GET("{fullUrl}")
    fun getFilm(@Path(value = "fullUrl", encoded = true ) fullUrl:String):Call<FilmCloud>*/


    @GET("people/")
    suspend fun search(@Query("search") search:String):ResponseWrapper<ArrayList<PersonCloud>>

    @GET("people/{index}")
    fun getPersonByIndex(@Path("index") index:Int):Call<PersonCloud>

    @GET("{fullUrl}")
    fun getFilm(@Path(value = "fullUrl", encoded = true ) fullUrl:String):Call<FilmCloud>


}