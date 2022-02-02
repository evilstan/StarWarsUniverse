package com.evilstan.starwarsuniverse.ui

//
data class Event<out T>(val status: Status, val data: T?) {

    companion object {
        fun <T> loading(): Event<T> = Event( Status.LOADING, null)

        fun <T> success(data: T?): Event<T> = Event(Status.SUCCESS, data)

        fun <T> error(): Event<T> = Event(Status.ERROR, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
