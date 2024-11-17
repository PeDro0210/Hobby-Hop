package com.pedro0210.hobbylobby.data.network

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.pedro0210.hobbylobby.domain.util.NetworkError
import com.pedro0210.hobbylobby.domain.util.Result

suspend inline fun <T> responseToResult(call: () -> Result<T, NetworkError>): Result<T, NetworkError> {
    return try {
        call() // Directly propagate the Result from the call
    } catch (e: FirebaseAuthException) {
        // Handle Firebase-specific exceptions
        when (e.errorCode) {
            "ERROR_INVALID_CREDENTIAL" -> Result.Error(NetworkError.INVALID_CREDENTIAL)
            "ERROR_USER_NOT_FOUND" -> Result.Error(NetworkError.USER_NOT_FOUND)
            "ERROR_EMAIL_ALREADY_IN_USE" -> Result.Error(NetworkError.EMAIL_ALREADY_IN_USE)
            else -> Result.Error(NetworkError.FIREBASE_GENERIC)
        }
    } catch (e: FirebaseException) {
        // Handle generic Firebase exceptions
        Result.Error(NetworkError.FIREBASE_GENERIC)
    }
}

