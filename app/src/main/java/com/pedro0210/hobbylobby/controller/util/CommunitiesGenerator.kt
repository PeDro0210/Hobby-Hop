package com.pedro0210.hobbylobby.controller.util
import kotlin.random.Random

//TODO: see where to refactor this
data class Community( //ths structure of this will almoast be the same
    val title:String,
    val description:String,
    val image:String,
    val partOfCommunity:Boolean,
    val id: String
)


fun generateRandomCommunities(n: Int): List<Community> {
    return List(n) {
        Community(
            title = getRandomString(10),
            description = getRandomString(120),
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFPBpBGyZzdup3WRZlpdZaAls4H-IFw6x9Bg&s",
            id = getRandomString(10),
            partOfCommunity = Random.nextBoolean()

        )
    }
}
