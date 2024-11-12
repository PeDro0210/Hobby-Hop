package com.pedro0210.hobbylobby.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.api.Context
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException

class AuthRepo {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()



    suspend fun manageAuth(email: String, password: String): String {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            authResult.user?.uid ?: "Id Error" //returns the uid
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            signUp(email = email, password = password) //does the signing with firebase and the uid
        } catch (e: Exception) {
            "ERROR"
        }
    }


    private suspend fun signUp(email: String, password: String): String {
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            return authResult.user?.uid ?: "Id Error"
        } catch (e: Exception){
            return "ERROR"
        }
    }


    suspend fun creteUser(username: String, id: String): String {


        return "PLACEHOLDER_ID"
    }


   suspend fun getUsername(id: String): String {
       val document = firestore.collection("users").document(id).get().await()
       return document.getString("username") ?: ""
   }

   suspend fun getPfp(id: String): String {
       val document = firestore.collection("users").document(id).get().await()
       return document.getString("pfp") ?: ""

   }



    //TODO: add each loginEmail and registerEmail for X, Google and Facebook
}