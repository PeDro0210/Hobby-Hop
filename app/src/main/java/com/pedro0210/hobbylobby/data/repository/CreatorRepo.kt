package com.pedro0210.hobbylobby.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.presentation.model.CommunityCreation
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class CreatorRepo (
){

    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var Communities: CollectionReference
    private val storage = FirebaseStorage.getInstance()

    suspend fun getBigCommunityId(BigCommunityName: String): String {

        val id = firestore.collection("big_communities").whereEqualTo("name", BigCommunityName).get().await().documents.firstOrNull()?.id
        if (id != null){
            return id.toString()
        } else {
            return "Error"
        }
    }


    suspend fun verifyComunityName(
        BigCommunityId: String,
        CommunityName: String
    ): Boolean {
        return try {
            val name = firestore.collection("big_communities").document(BigCommunityId).collection("communities").whereEqualTo("name", CommunityName).get().await().documents.firstOrNull()?.id
            if (name != null) {
                false
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
        BigCommunityId: String
    ): String {
        return try {
            val bigCommunityName = firestore.collection("big_communities").document(BigCommunityId).get().await().get("name")

            val communityImageRef = storage.reference.child("big_communities/$bigCommunityName/communities/$title/pfp.png")
            communityImageRef.putFile(image!!).await()
            val imageUrl = communityImageRef.downloadUrl.await()
            val community = mapOf(
                "name" to title,
                "description" to description,
                "pfp" to imageUrl
            )
            firestore.collection("big_communities").document(BigCommunityId).collection("communities").add(community).await()
            val SCid = firestore.collection("big_communities").document(BigCommunityId)
                .collection("communities").whereEqualTo("name", title).get().await()
                .documents.firstOrNull()?.id
            if (SCid != null) {
                return SCid
            } else {
                println("Error at finding the community")
                return "Error"
            }
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }

    }

    suspend fun makeUserAdmin(
        userid: String,
        cummunityId: String,
        BigCommunityId: String
    ): String{
        return try{
            val communityReference = firestore.collection("big_communities").document(BigCommunityId).collection("communities").document(cummunityId)


            val communityAdminList = firestore.collection("users").document(userid).get().await().get("community_admin") as? MutableList<DocumentReference> ?: mutableListOf()
            // Add the community reference to the list
            communityAdminList.add(communityReference)
            // Update the user document with the new list
            firestore.collection("users").document(userid).update("community_admin", communityAdminList).await()
            return "Success"
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }

    }

    suspend fun addrooms(
        rooms: List<CommunityCreation>,
        cummunityId: String,
        userId: String,
        BigCommunityId: String
    ): String {
        return try {
            firestore.collection("big_communities").document(BigCommunityId).collection("communities").document(cummunityId).collection("rooms").get().await()
            val communityName = firestore.collection("big_communities").document(BigCommunityId).collection("communities").document(cummunityId).get().await().get("name")
            val bigCommunityName = firestore.collection("big_communities").document(BigCommunityId).get().await().get("name")
            rooms.forEach {
                val userReference = firestore.collection("users").document(userId)
                val users = listOf<DocumentReference>(userReference)
                val communityImageRef = storage.reference.child("big_communities/$bigCommunityName/communities/$communityName/${it.title}/pfp.png")
                communityImageRef.putFile(it.image!!).await()
                val imageUrl = communityImageRef.downloadUrl.await()
                val room = mapOf(
                    "name" to it.title,
                    "description" to it.description,
                    "pfp" to imageUrl,
                    "users" to users,
                    "users_to_accept" to listOf<DocumentReference>()
                )
                firestore.collection("big_communities").document(BigCommunityId).collection("communities").document(cummunityId).collection("rooms").add(room).await()
            }
            return "Success"
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }

    }

}




    //TODO: verify bigcomunity name (New parameter), check if the name is already taken, if it is, return an error   **DONE
    //TODO: if not just create it and then create each room **DONE
    //To create data refence authrepo, create user





    //TODO: Admin group reference Comunity repo, actual country and pass it to the user **DONE
    //TODO: userData and there collect the user id, and pass it to the creator repo, getID **DONE




    //Rooms data:
    //BigCom: descripcion, nombre, pfp
    //create a collection with every comunity, and then a subcollection with the rooms
    //Rooms: add the list, something like add al the list or append
    //fields for rooms: users, users_to_accept, name, description, pfp, id
