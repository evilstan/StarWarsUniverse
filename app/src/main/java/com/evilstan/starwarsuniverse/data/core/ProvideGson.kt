package com.evilstan.starwarsuniverse.data.core

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class ProvideGson {

    fun factory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
        .setLenient()
        .create())
}