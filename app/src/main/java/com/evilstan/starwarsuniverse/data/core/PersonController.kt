package com.evilstan.starwarsuniverse.data.core

import com.evilstan.starwarsuniverse.data.dictionary.PersonCloud
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PersonController : Callback<PersonCloud> {
    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val starWarsApi: StarWarsApi = retrofit.create(StarWarsApi::class.java)
        val call: Call<PersonCloud> = starWarsApi.getPersonByIndex(1)
        call.enqueue(this)
    }

    lateinit var person:PersonCloud

    override fun onResponse(call: Call<PersonCloud>, response: Response<PersonCloud>) {
        if (response.isSuccessful) {
            person = response.body()!!
            println(person)
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<PersonCloud>, t: Throwable) {
        t.printStackTrace()
    }

    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"
    }


}