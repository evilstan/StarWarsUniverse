package com.evilstan.starwarsuniverse.data.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvideRetrofit(
    private val baseURL: String,
    private val gsonConverterFactory: GsonConverterFactory
) {
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}