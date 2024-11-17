package com.pedro0210.hobbylobby.presentation.widgets.communities.util

import androidx.navigation.NavController
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.navigation.AdminCommunity
import com.pedro0210.hobbylobby.presentation.navigation.AdminRoom
import com.pedro0210.hobbylobby.presentation.navigation.BigCommunity
import com.pedro0210.hobbylobby.presentation.navigation.Requests
import com.pedro0210.hobbylobby.presentation.navigation.Rooms
import com.pedro0210.hobbylobby.presentation.navigation.SmallCommunity
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToAdminCommunities
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToAdminRooms
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToBigCommunities
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToRequests
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToRooms
import com.pedro0210.hobbylobby.presentation.navigation.routers.navigateToSmallCommunities

fun routingNormalCommunities(
    image: String,
    name: String,
    description: String,
    id: String,
    partOfCommunity: Boolean,
    navController: NavController,
    type: CommunityType,

){
    /*
   * TODO: assign different navigation depending on the button type
   *
   *   buttonType: bigCommunity -> navigate to BigCommunity
   *   buttonType: smallCommunity -> navigate to SmallCommunity
   *   buttonType: room -> navigate to Room
   */
    println(type)
    when (type){
        CommunityType.country -> navController.navigateToBigCommunities(
            BigCommunity(
                id = id,
                image = image,
                title = name,
                description = description,
                partOfCommunity = partOfCommunity,
            )
        )
        CommunityType.communities -> navController.navigateToSmallCommunities(
            SmallCommunity(
                id = id,
                image = image,
                title = name,
                description = description,
                partOfCommunity = partOfCommunity,
            )
        )
        CommunityType.rooms -> navController.navigateToRooms(
            Rooms(
                id = id,
                image = image,
                title = name,
                description = description,
                partOfCommunity = partOfCommunity
            )
        )
    }
}


fun routingSettingsCommunities(
    image: String,
    name: String,
    description: String,
    id: String,
    partOfCommunity: Boolean,
    navController: NavController,
    type: CommunityType,
    ){

    //TODO: it will just do the navigation for the rooms and communities
    when (type){
        CommunityType.rooms ->{ navController.navigateToRequests(
                Requests(
                    id = id,
                    title = name,
                    image = image,
                    description = description
                )
            )
        }

        CommunityType.communities -> navController.navigateToAdminRooms(
            AdminRoom(
                id = id,
                image = image,
                title = name,
                description = description,

            )
        )
        CommunityType.country -> {
            println("This should not happen")
        }

    }
}
