package com.evilstan.starwarsuniverse.ui

class ErrorMessage() {
    companion object {
        fun message(errorCode: ErrorCode) =
            when (errorCode) {
                ErrorCode.CHARACTER_NOT_FOUND -> "Not found"
                ErrorCode.NO_INTERNET -> "No connection"
                ErrorCode.UNKNOWN_ERROR -> "No connection"
            }
    }

    enum class ErrorCode {
        NO_INTERNET,
        CHARACTER_NOT_FOUND,
        UNKNOWN_ERROR
    }
}