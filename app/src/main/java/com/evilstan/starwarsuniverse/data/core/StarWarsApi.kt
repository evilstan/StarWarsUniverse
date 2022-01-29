package com.evilstan.starwarsuniverse.data.core

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.data.dictionary.HomeWorldCloud
import com.evilstan.starwarsuniverse.data.dictionary.PeopleCloud
import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApi {
    @GET("people/?search={search}")
    fun searchCharacter(@Path("search") search:String):Call<PeopleCloud>

    @GET("people/{index}")
    fun getPersonByIndex(@Path("index") index:Int):Call<PersonCloud>

    @GET("films/")
    fun getFilmById(@Path("id") id:Int):Call<FilmCloud>

    @GET("planets/")
    fun getHomeWorldById(@Path("id") id:Int):Call<HomeWorldCloud>

    @GET
    fun getHomeWorldByUrl(@Url ulr:String):Call<HomeWorldCloud>
}