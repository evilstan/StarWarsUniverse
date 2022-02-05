package com.evilstan.starwarsuniverse.ui.core

//
data class Event<out T>(val status: Status, val data: T?, val errorCode: ErrorMessage.ErrorCode?) {

    companion object {
        fun <T> loading(): Event<T> = Event(Status.LOADING, null, null)

        fun <T> success(data: T?): Event<T> = Event(Status.SUCCESS, data, null)

        fun <T> error(errorCode: ErrorMessage.ErrorCode?): Event<T> =
            Event(Status.ERROR, null, errorCode)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
