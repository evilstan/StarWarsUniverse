package com.evilstan.starwarsuniverse.ui.core

class ErrorMessage{
    companion object {
        fun message(errorCode: ErrorCode) =
            when (errorCode) {
                ErrorCode.CHARACTER_NOT_FOUND -> "No such character found"
                ErrorCode.NO_INTERNET -> "No internet connection"
                ErrorCode.UNKNOWN_ERROR -> "Internal error. Try again later"
            }
    }

    enum class ErrorCode {
        NO_INTERNET,
        CHARACTER_NOT_FOUND,
        UNKNOWN_ERROR
    }
}