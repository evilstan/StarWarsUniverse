package com.evilstan.starwarsuniverse.data.core

import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.logging.HttpLoggingInterceptor

class NetModule {

    private val retrofit = ProvideRetrofit(
        BASE_URL,
        ProvideGson().factory()
    )
        .retrofit()

    fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)


    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"
    }
}