package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class UserRepo {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getDescription(id: String): Flow<String> {
        return try{
            val userRef = firestore.collection("users").document(id)
            val userDoc = userRef.get().await()
            val description = userDoc.getString("bio") ?: "" //brandon's convention
            flowOf(description)
        } catch (e: Exception){
            println(e)
            flowOf("")
        }
    }

    suspend fun getSocials(id: String): Flow<List<SocialMedia>> {
        return try{

            val socialsRef = firestore.collection("users").document(id).collection("socials")
            val socialDoc = socialsRef.get().await()
            println(socialDoc.documents)
            flowOf(
                socialDoc.documents.map { doc ->
                    SocialMedia(
                        image = doc.getString("pfp") ?: "",
                        name = doc.getString("name") ?: "",
                        url = doc.getString("link") ?: ""
                    )
                }
            )


        }catch (e: Exception){
            println(e)
            flowOf(emptyList())
        }
    }

}
