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
            println("RoomDoc: $roomDoc")

            val membersRef = roomDoc?.get("users") as? List<DocumentReference>?: emptyList()

            println("MembersRef: $membersRef")

            val isUserInRoom = membersRef.any { ref ->
                val userDoc = ref.get().await()
                userDoc.id == userId
            }

            println("isUserInRoom: $isUserInRoom")
            if (!isUserInRoom) {

                val usersToAccept = roomDoc?.get("users_to_accept") as? MutableList<DocumentReference> ?: mutableListOf()
                val userRef = firestore.collection("users").document(userId)

                println("UsersToAccept: $usersToAccept")
                println("UserRef: $userRef")

                usersToAccept.add(userRef)

                roomDoc?.reference?.update("users_to_accept", usersToAccept)?.await()

            }

            true
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }

    suspend fun removeUserFromRoom(roomId: String, userId: String): Boolean {
        return try {

            val roomDoc = firestore.collectionGroup("rooms").get().await().find { it.id == roomId }
            println("RoomDoc: $roomDoc")


            val membersRef = roomDoc?.get("users") as? MutableList<DocumentReference> ?: mutableListOf()
            println("MembersRef: $membersRef")

            val userRef = firestore.collection("users").document(userId)
            val isUserInRoom = membersRef.contains(userRef)
            println("isUserInRoom: $isUserInRoom")

            if (isUserInRoom) {

                membersRef.remove(userRef)


                roomDoc?.reference?.update("users", membersRef)?.await()
                println("User removed from the room")
                return true
            } else {
                println("User not in the room")
                return false
            }
        } catch (e: Exception) {
            println("Error removing user from room: ${e.message}")
            false
        }
    }


    suspend fun checkIfUserIsInRoom(
        roomId: String,
        userId: String
    ): Boolean {
        return try {
            val roomDoc = firestore.collectionGroup("rooms").get().await().find { it.id == roomId }
            println("RoomDoc: $roomDoc")

            val membersRef = roomDoc?.get("users") as? List<DocumentReference> ?: emptyList()

            println("MembersRef: $membersRef")

            val isUserInRoom = membersRef.any { ref ->
                val userDoc = ref.get().await()
                userDoc.id == userId
            }
            isUserInRoom
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }

    suspend fun checkIfUserIsInRequest(
        roomId: String,
        userId: String
    ): Boolean {
        return try {
            val roomDoc = firestore.collectionGroup("rooms").get().await().find { it.id == roomId }
            println("RoomDoc: $roomDoc")

            val membersRef = roomDoc?.get("users_to_accept") as? List<DocumentReference> ?: emptyList()

            println("MembersRef: $membersRef")

            val isUserInRoom = membersRef.any { ref ->
                val userDoc = ref.get().await()
                userDoc.id == userId
            }
            isUserInRoom
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }

        }

