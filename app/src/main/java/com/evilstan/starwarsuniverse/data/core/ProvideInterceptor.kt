package com.evilstan.starwarsuniverse.data.core

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class ProvideInterceptor(private val isDebug: Boolean) {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    fun interceptor(): Interceptor = interceptor

}