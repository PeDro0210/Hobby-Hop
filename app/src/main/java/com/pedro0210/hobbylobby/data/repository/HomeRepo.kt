package com.pedro0210.hobbylobby.data.repository

import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HomeRepo(
    userPreferences: UserPreferences, //for fetching the id
) {

    
    suspend fun getCountries(): Flow<List<Community>> {
        //TODO: call the repo with the firebase implementation
        return flowOf(
            listOf(
                Community(
                    title = "United States",
                    description = "A country in North America.",
                    image = "us.png",
                    partOfCommunity = true,
                    id = "1",
                    type = CommunityType.bigCommunity
                ),
                Community(
                    title = "France",
                    description = "A country in Western Europe.",
                    image = "fr.png",
                    partOfCommunity = true,
                    id = "2",
                    type = CommunityType.bigCommunity
                ),
                Community(
                    title = "Japan",
                    description = "An island nation in East Asia.",
                    image = "jp.png",
                    partOfCommunity = true,
                    id = "3",
                    type = CommunityType.bigCommunity
                )
            )
        )
    }

    suspend fun getOwnCommunities(): Flow<List<Community>> {
        //TODO: call the repo with the firebase implementation
        return flowOf(
            listOf(
                Community(
                    title = "Hiking",
                    description = "Explore trails and enjoy nature.",
                    image = "hiking.png",
                    partOfCommunity = true,
                    id = "1",
                    type = CommunityType.smallCommunity
                ),
                Community(
                    title = "Photography",
                    description = "Capture beautiful moments and landscapes.",
                    image = "photography.png",
                    partOfCommunity = false,
                    id = "2",
                    type = CommunityType.smallCommunity
                ),
                Community(
                    title = "Cooking",
                    description = "Learn new recipes and cooking techniques.",
                    image = "cooking.png",
                    partOfCommunity = true,
                    id = "3",
                    type = CommunityType.smallCommunity
                ),
                Community(
                    title = "Yoga",
                    description = "Improve flexibility and reduce stress.",
                    image = "yoga.png",
                    partOfCommunity = false,
                    id = "4",
                    type = CommunityType.smallCommunity
                ),
                Community(
                    title = "Cycling",
                    description = "Ride bikes for fitness or leisure.",
                    image = "cycling.png",
                    partOfCommunity = true,
                    id = "5",
                    type = CommunityType.smallCommunity
                )
            )
        )
    }

}