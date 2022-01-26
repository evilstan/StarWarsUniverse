package com.evilstan.starwarsuniverse.data.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ProvideOkHttpClientBuilder(
    private val interceptor: Interceptor,
    private val readTimeout: Long = READ_TIMEOUT,
    private val connectTimeout: Long = CONNECT_TIMEOUT
) {

    companion object {
        private const val READ_TIMEOUT = 60L
        private const val CONNECT_TIMEOUT = 60L
    }

    fun okHttpClientBuilder() = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
}
