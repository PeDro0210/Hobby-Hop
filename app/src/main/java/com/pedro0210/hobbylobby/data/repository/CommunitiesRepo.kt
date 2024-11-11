package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await


class CommunitiesRepo{

    private val firestore = FirebaseFirestore.getInstance()

    private lateinit var actualCommunity: CollectionReference

    /* double purpose, getting Rooms and getting communities */
    suspend fun getCommunities(
        type: CommunityType,
        id: String
    ): Flow<List<Community>> {

        return try {
            when (type) {

                // Retrieve data for country-type communities
                CommunityType.country -> {
                    actualCommunity = firestore.collection("big_communities").document(id).collection("communities")
                    val communitiesDoc  = actualCommunity.get().await()
                    flowOf(
                        communitiesDoc.documents.map { doc ->
                            Community(
                                title = doc.getString("name") ?: "",
                                description = doc.getString("description") ?: "",
                                image = doc.getString("pfp") ?: "",
                                id = doc.id,
                                type = CommunityType.communities,
                                partOfCommunity = true
                            )
                        }
                    )
                }

                // Retrieve data for communities-type (technically rooms)
                CommunityType.communities -> {

                        val roomsDoc = actualCommunity.document(id).collection("rooms").get().await()
                        println("ROOMS: ${roomsDoc.documents}")
                        flowOf(
                            roomsDoc.documents.map{ doc->
                            Community(
                                title = doc.getString("name") ?: "",
                                description = doc.getString("description") ?: "",
                                image = doc.getString("pfp") ?: "",
                                id = doc.id,
                                type = CommunityType.rooms,
                                partOfCommunity = true
                            )
                            }
                        )
                }

                // Empty flow for rooms or unsupported types (handle if needed)
                CommunityType.rooms -> flowOf(emptyList())
            }
        } catch (e: Exception) {
            println(e.message)
            flowOf(emptyList())
        }
    }


}
