package com.pedro0210.hobbylobby.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.api.Context
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class AuthRepo {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()


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
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.uid ?: "Id Error"
        } catch (e: Exception){
            "ERROR"
        }
    }


    suspend fun creteUser(username: String, id: String, pfpUri: Uri): String {
        return try {
            val userImageRef = storage.reference.child("users/$username/pfp.png")

            userImageRef.putFile(pfpUri) //fucking listeners, fuck them, fuck them all
            .addOnSuccessListener { taskSnapshot ->
                // After the file is uploaded, get the download URL
                userImageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Successfully fetched the download URL
                    val pfpUrl = uri.toString()
                    println("Profile picture URL: $pfpUrl")
                    val user = mapOf( //lmao, no need to do fucking DTO's for everything
                        "username" to username,
                        "pfp" to pfpUrl,
                        "admin_rooms" to emptyList<Any>()  // Initially empty array for future references
                    )

                    firestore.collection("users").document(id).set(user)

                    id
                }
        }

            id  //returns the id
        } catch (e: CancellationException) {
            println("Coroutine was cancelled: $e")
            return "ERROR"
        } catch (e: Exception) {
            println("Error creating user: $e")
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



    //TODO: add each loginEmail and registerEmail for X, Google and Facebook
}