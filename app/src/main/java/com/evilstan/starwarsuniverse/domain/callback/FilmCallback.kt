package com.evilstan.starwarsuniverse.domain.callback

import com.evilstan.starwarsuniverse.data.dictionary.FilmCloud
import com.evilstan.starwarsuniverse.domain.mapper.FilmMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmCallback : Callback<FilmCloud> {
    lateinit var filmCloud: FilmCloud
    var success: Boolean = false

    override fun onResponse(call: Call<FilmCloud>, response: Response<FilmCloud>) {
        if (response.isSuccessful) {
            filmCloud = response.body()!!
            success = true
        }
    }

    override fun onFailure(call: Call<FilmCloud>, t: Throwable) {
        t.printStackTrace()
    }
}