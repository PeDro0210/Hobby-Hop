package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await

class AdminRepo{

    val firestore = FirebaseFirestore.getInstance()

    suspend fun getUsersToAccept(
        id:String
    ): Flow<List<RoomMember>> {
        return try{

            val roomRef = firestore.collectionGroup("rooms").get().await().find { it.id == id }
            val usersToAccept = roomRef?.get("users_to_accept") as? List<DocumentReference>

            val users = usersToAccept!!.mapIndexed { index, ref->
                val doc = ref.get().await()

                RoomMember(
                    name = doc.getString("username")?:"",
                    pfp = doc.getString("pfp")?:"",
                    id = doc.id
                )

            }

            flowOf(users)
        }catch (e:Exception){
             flowOf(emptyList())
        }

    }

    suspend fun addUserToCommunity(
        roomId: String,
        userId:String
    ){
        try{

            val roomRef = firestore.collectionGroup("rooms").get().await().find { it.id == roomId}
            val usersToAccept = roomRef?.get("users_to_accept") as? List<DocumentReference>

            val userToAccept = usersToAccept?.find {it.id==userId}

            val usersAccepted = roomRef?.get("users") as? MutableList<DocumentReference?>  ?: mutableListOf()

            usersAccepted.add(userToAccept)


            val updatedUsersToAccept = usersToAccept?.minus(userToAccept)
            roomRef?.reference?.update("users_to_accept", updatedUsersToAccept)?.await()
            roomRef?.reference?.update("users", usersAccepted)?.await()



        }catch (e:Exception){
            println("Error ${e}")
        }
    }

    suspend fun denyUserToCommunity(
        roomId: String,
        userId:String
    ){
        try{

            val roomRef = firestore.collectionGroup("rooms").get().await().find { it.id == roomId}
            val usersToAccept = roomRef?.get("users_to_accept") as? List<DocumentReference>

            val userToAccept = usersToAccept?.find {it.id==userId}

            val usersAccepted = roomRef?.get("users") as? MutableList<DocumentReference?>  ?: mutableListOf()

            usersAccepted.add(userToAccept)

            roomRef?.reference?.update("users", usersAccepted)?.await()

            val updatedUsersToAccept = usersToAccept?.minus(userToAccept)
            roomRef?.reference?.update("users_to_accept", updatedUsersToAccept)?.await()




        }catch (e:Exception){
            println("Error ${e}")
        }
    }

}