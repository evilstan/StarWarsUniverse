package com.evilstan.starwarsuniverse.cloud.core

class NetModule {

    private val retrofit = ProvideRetrofit(BASE_URL).retrofit()

    fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)


    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"
    }
}