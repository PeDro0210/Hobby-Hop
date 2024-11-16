package com.pedro0210.hobbylobby.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await


class CommunitiesRepo{

    private val firestore = FirebaseFirestore.getInstance()

    private lateinit var actualCountry: CollectionReference

    /* double purpose, getting Rooms and getting communities */
    suspend fun getCommunities(
        type: CommunityType,
        id: String
    ): Flow<List<Community>> {

        return try {
            //Get's collection depending in which kind of CommunityType has
            when (type) {

                CommunityType.country -> {
                    /*
                     * as we know, all this statements will be called in a one way flow
                     * country -> communities -> rooms
                     *
                     * as we know that, we'll be storing the information of the biggest actualCountry for getting the communities out of it
                     */
                    actualCountry = firestore.collection("big_communities").document(id).collection("communities") //saves the document of communities from the country press
                    val communitiesDoc  = actualCountry.get().await() // just calling it
                    //maps it
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

                CommunityType.communities -> {

                    /*
                     * as said before, we need to get the information of the actualCountry cause' of the way our firebase document of big_communities work
                     * that's why actualCountry is called again
                     * TODO: see how to get the reference of rooms for managing the users display
                     */

                    val roomsDoc = actualCountry.document(id).collection("rooms").get().await()
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

                CommunityType.rooms -> flowOf(emptyList())
            }
        } catch (e: Exception) {
            println(e.message)
            flowOf(emptyList())
        }
    }
    //TODO: refactor this for using id just for the users, and pass ref in another way
    suspend fun getAdminCommunities(
        type: CommunityType,
        id: String = "",
    ): Flow<List<Community>> {

        return try {
            when (type) {

                // Retrieve data for country-type communities
                CommunityType.country -> {
                    println("this should not be called")
                    flowOf(emptyList())
                }

                CommunityType.communities -> {

                    /*
                    * this is other way of doing what we did above, but with the difference that instead of knowing in what "document flow" we are, we just take the reference of the documents
                    *
                    * TODO: if time, change the way of getting the info as this
                    *
                    * In here we first take the references of communities that users have in there fields
                    *
                    */

                     val userRef = firestore.collection("users").document(id)  //get's the user

                    val userDoc = userRef.get().await() //gets the doc

                    val adminCommunityRefs = userDoc["community_admin"] as? List<DocumentReference> ?: emptyList() //see's the field of community_admin for getting the references

                    val communities = adminCommunityRefs.mapIndexed { index, ref -> // Use mapIndexed for ordered access

                        val doc = ref.get().await() // gets the doc

                        Community(
                            title = doc.getString("name") ?: "",
                            description = doc.getString("description") ?: "",
                            image = doc.getString("pfp") ?: "",
                            id = ref.path, // Use the reference path as ID or pass ref.id for the document's unique ID
                            type = CommunityType.communities,
                            partOfCommunity = true
                        )
                    }


                    return flowOf(communities)
                }

                // Same Logic as getCommunities in the room part
                CommunityType.rooms -> {
                    /*
                    *
                    * in this case we pass it the total reference (we use the parameter id as 2 different possibles thing)
                    * for getting the rooms, as in the normal get communities, we have the issue, of getting the information for the room for later things
                    * but yeah, you can handle it :D
                    * */
                    val roomsDoc = firestore.document(id).collection("rooms").get().await()
                    println("ROOMS: ${roomsDoc.documents}")
                    println("ROOMS: ${roomsDoc.documents}")
                    return flowOf(
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
            }
        } catch (e: Exception) {
            println(e.message)
            flowOf(emptyList())
        }
    }


}
