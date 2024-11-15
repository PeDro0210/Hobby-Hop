package com.pedro0210.hobbylobby.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class CreatorRepo {

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var Communities: CollectionReference
    private val storage = FirebaseStorage.getInstance()


    suspend fun verifyComunityName(
        BigCommunityName: String,
        CommunityName: String
    ): Boolean {
        return try {
            val id =
                firestore.collection("big-communities").whereEqualTo("name", BigCommunityName).get()
                    .await().documents.firstOrNull()?.id
            if (id != null) {
                Communities =
                    firestore.collection("big-communities").document(id).collection("communities")
                val comunitiesResult = Communities.get().await()
                flowOf(
                    comunitiesResult.documents.map { doc ->
                        Community(
                            title = doc.getString("name") ?: "",
                            description = doc.getString("description") ?: "",
                            image = doc.getString("pfp") ?: "",
                            id = doc.id,
                            type = CommunityType.smallCommunity,
                            partOfCommunity = true
                        )
                    }
                )
                !comunitiesResult.documents.any { it.getString("name") == CommunityName }
            } else {
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun createCommunity(
        title: String,
        description: String,
        image: Uri,
        userId: String,
        BigCommunityName: String, ): String {
        val id =
            firestore.collection("big-communities").whereEqualTo("name", BigCommunityName).get()
                .await().documents.firstOrNull()?.id
        //TODO: call the repo with the firebase implementation
        //This is pure dummy data
        /*
        return Community(
            title = title,
            description = description,
            image = image,
            partOfCommunity = true,
            id = "1",
            type = CommunityType.smallCommunity
        )
         */
        if (id != null) {
            return try {
                val communityImageRef = storage.reference.child("communities/$title/pfp.png")
                communityImageRef.putFile(image).await()
                val imageUrl = communityImageRef.downloadUrl.await()
                val community = mapOf(
                    "name" to title,
                    "description" to description,
                    "pfp" to imageUrl.toString(),
                    "users" to listOf(userId)
                )
                firestore.collection("big-communities").document(id).collection("communities")
                    .add(community).await()

                id
            } catch (e: CancellationException) {
                println("Corrutine was cancelled: $e")
                return "Error"
            } catch (e: Exception) {
                println("Error: $e")
                return "Error"
            }


        }else{
            return "Error"
        }
    }
}



    //TODO: Implement the image upload thingy **DONE
    //To image reference profile creation SCreen LaunchedEffect, renderize uri
    //choseimage, authviwemodel

    //TODO: create a func to pickup all the cuminity data, and the rooms data that is a list         rooms: List<Community>
    //TODO: verify bigcomunity name (New parameter), check if the name is already taken, if it is, return an error   **DONE
    //TODO: if not just create it and then create each room  **HALF DONE
    //To create data refence authrepo, create user



    //TODO: if the operation is not done, wait              Viewmodel STUFF
    //Reference LoginScreen LaunchedEffect, is loading state




    //Rooms data:
    //BigCom: descripcion, nombre, pfp
    //create a collection with every comunity, and then a subcollection with the rooms
    //Rooms: add the list, something like add al the list or append
    //fields for rooms: users, users_toaccept, name, description, pfp, id
}