package com.pedro0210.hobbylobby.data.repository

import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOf


class CommunitiesRepo {


    /* double purpose, getting Rooms and getting communities */
    suspend fun getCommunities(
        type: CommunityType,
        id: String
    ): Flow<List<Community>> {
        when (type) {
            //TODO: call the repo with the firebase implementation
            //This is pure dummy data
            CommunityType.country -> {
                return flowOf(
                    listOf(
                        Community(
                            title = "Universidad de San Carlos de Guatemala",
                            description = "The only public university in Guatemala.",
                            image = "usac.png",
                            partOfCommunity = true,
                            id = "1",
                            type = CommunityType.smallCommunity
                        ),
                        Community(
                            title = "Universidad Francisco Marroquín",
                            description = "Known for its focus on business and economics.",
                            image = "ufm.png",
                            partOfCommunity = false,
                            id = "2",
                            type = CommunityType.smallCommunity
                        ),
                        Community(
                            title = "Universidad del Valle de Guatemala",
                            description = "A private university specializing in technology and sciences.",
                            image = "uvg.png",
                            partOfCommunity = true,
                            id = "3",
                            type = CommunityType.smallCommunity
                        )
                    )
                )
            }
            CommunityType.smallCommunity -> {
                /*
                * These are technically rooms, but community buttons
                * and room buttons share the same structure, except
                * the navigation, that's why I decided to call rooms in here
                */
                return flowOf(
                    listOf(
                        Community(
                            title = "Sports Club",
                            description = "Participate in football, basketball, and more.",
                            image = "sports.png",
                            partOfCommunity = false,
                            id = "1",
                            type = CommunityType.rooms
                        ),
                        Community(
                            title = "Art Workshop",
                            description = "Explore painting, drawing, and sculpture.",
                            image = "art.png",
                            partOfCommunity = true,
                            id = "2",
                            type = CommunityType.rooms
                        ),
                        Community(
                            title = "Student Council",
                            description = "Join and represent your fellow students.",
                            image = "council.png",
                            partOfCommunity = true,
                            id = "3",
                            type = CommunityType.rooms
                        ),
                        Community(
                            title = "Science Club",
                            description = "Engage in research and scientific discussions.",
                            image = "science.png",
                            partOfCommunity = false,
                            id = "4",
                            type = CommunityType.rooms
                        )
                    )
                )
            }
            CommunityType.rooms -> {
                return flowOf()
            }
        }
    }
}
