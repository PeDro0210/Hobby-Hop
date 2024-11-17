package com.pedro0210.hobbylobby.domain.util

interface Error

enum class NetworkError : Error {

    INVALID_CREDENTIAL,
    USER_NOT_FOUND,
    EMAIL_ALREADY_IN_USE,

    FIREBASE_GENERIC
}
