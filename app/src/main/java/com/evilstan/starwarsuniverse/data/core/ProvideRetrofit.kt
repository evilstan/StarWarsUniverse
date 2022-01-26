package com.evilstan.starwarsuniverse.data.core

import retrofit2.Retrofit

class ProvideRetrofit(
    private val baseURL: String,
    private val provideClientBuilder: ProvideOkHttpClientBuilder
) {
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(provideClientBuilder.okHttpClientBuilder().build())
        .build()
}