package com.evilstan.starwarsuniverse.data.core

class NetModule {

    private val retrofit = ProvideRetrofit(
        BASE_URL,
        ProvideOkHttpClientBuilder(ProvideInterceptor(false).interceptor())
    ).retrofit()

    fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)


    companion object {
        private const val BASE_URL = "https://https://swapi.dev/api/"
    }
}