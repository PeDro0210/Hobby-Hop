package com.pedro0210.hobbylobby.data.repository

class LoginRepo {


    suspend fun login(email: String, password: String): String {
        //dummy repo, for the moment
        //TODO: call the repo with the firebase implementation

        return "PLACEHOLDER_ID"
    }
}