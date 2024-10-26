package com.pedro0210.hobbylobby.presentation.util
import com.pedro0210.hobbylobby.presentation.model.Community
import kotlin.random.Random



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
