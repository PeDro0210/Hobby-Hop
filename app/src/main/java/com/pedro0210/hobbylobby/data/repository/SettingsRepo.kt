package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.data.datastore.UserData


class SettingsRepo(
    private val userPreferences: UserData
) {

    private val firestore = FirebaseFirestore.getInstance()


    suspend fun getUserName(): String {


        return "PLACEHOLDER"
    }

    suspend fun getPfps(): String {

        return "PLACEHOLDER"
    }



    //TODO: add each loginEmail and registerEmail for X, Google and Facebook
}
