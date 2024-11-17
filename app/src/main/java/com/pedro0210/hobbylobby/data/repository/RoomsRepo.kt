package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class RoomsRepo {

    val firestore = FirebaseFirestore.getInstance()

    suspend fun getUsers(
        id:String
    ): Flow<List<RoomMember>> {

        return try{
            //Search with the id the reference to id
            val roomDoc = firestore.collectionGroup("rooms").get().await().find{it.id == id}

            val membersRef = roomDoc?.get("users") as? List<DocumentReference>?: emptyList()

            println(membersRef)
            val member = membersRef.mapIndexed{ index, ref ->
                val doc = ref.get().await()

                RoomMember(
                    name = doc.getString("username")?: "",
                    pfp = doc.getString("pfp")?: "",
                    id = doc.id
                )
            }
            flowOf(member)
        }catch (e:Exception){
            flowOf(emptyList())
        }


    }

    suspend fun checkAndRequestJoin(
        roomId: String,
        userId: String
    ): Boolean {
        return try {
            val roomDoc = firestore.collectionGroup("rooms").get().await().find { it.id == roomId }
            print("RoomDoc: $roomDoc")
            if (roomDoc == null) return false


            val membersRef = roomDoc.get("users") as? List<DocumentReference> ?: emptyList()


            val isUserInRoom = membersRef.any { ref ->
                val userDoc = ref.get().await()
                userDoc.id == userId
            }

            if (!isUserInRoom) {

                val usersToAccept = roomDoc.get("users_to_accept") as? MutableList<DocumentReference> ?: mutableListOf()
                println(usersToAccept)
                val userRef = firestore.collection("users").document(userId)
                if (!usersToAccept.contains(userRef)) {
                    usersToAccept.add(userRef)
                    firestore.collection("rooms")
                        .document(roomDoc.id)
                        .update("users_to_accept", usersToAccept)
                        .await()
                }
            }

            true
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }

    suspend fun removeUserFromRoom(roomId: String, userId: String): Boolean {
        return try {
            val roomDoc = firestore.collection("rooms").document(roomId).get().await()
            val usersRef = roomDoc.get("users") as? MutableList<DocumentReference> ?: mutableListOf()


            val updatedUsers = usersRef.filterNot { it.id == userId }
            firestore.collection("rooms").document(roomId).update("users", updatedUsers).await()

            true
        } catch (e: Exception) {
            println("Error removing user from room: ${e.message}")
            false
        }
    }


}