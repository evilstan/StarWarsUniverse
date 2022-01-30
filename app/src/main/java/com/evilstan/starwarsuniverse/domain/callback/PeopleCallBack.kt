package com.evilstan.starwarsuniverse.domain.callback

import com.evilstan.starwarsuniverse.data.dictionary.PeopleCloud
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleCallBack : Callback<PeopleCloud> {
    lateinit var peopleCloud: PeopleCloud
    var success = false

    override fun onResponse(call: Call<PeopleCloud>, response: Response<PeopleCloud>) {
        if (response.isSuccessful) {
            peopleCloud = response.body()!!
            success = true
        }
    }

    override fun onFailure(call: Call<PeopleCloud>, t: Throwable) {
        t.printStackTrace()
    }
}