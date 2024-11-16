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

}