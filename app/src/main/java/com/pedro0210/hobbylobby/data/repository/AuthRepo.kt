package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepo {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()


    suspend fun manageAuth(email: String, password: String): String {
       try {
           val authResult = auth.signInWithEmailAndPassword(email, password).await()

           val id = if (authResult != null) {
               login(authResult)
           } else {
               signUp(email, password)
           }
           println(id);
           return id
       } catch (e: Exception) {
           println("error: ")
           println(e.message)
           return "ERROR"
       }
    }

   suspend fun getUsername(id: String): String {
       val document = firestore.collection("users").document(id).get().await()
       return document.getString("username") ?: ""
   }

   suspend fun getPfp(id: String): String {
       val document = firestore.collection("users").document(id).get().await()
       return document.getString("pfp") ?: ""

   }


    private fun login(authResult: AuthResult): String {
        return authResult.user?.uid ?: ""
    }

    suspend fun signUp(email: String, password: String): String {
        //TODO: do this later

        return "PLACEHOLDER_ID"
    }


    suspend fun signUp(username: String): String {
        //dummy repo, for the moment
        //TODO: call the repo with the firebase implementation


        return "PLACEHOLDER_ID"
    }


    //TODO: add each loginEmail and registerEmail for X, Google and Facebook
}