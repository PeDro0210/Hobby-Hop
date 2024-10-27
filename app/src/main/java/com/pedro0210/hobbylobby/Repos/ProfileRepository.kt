package com.pedro0210.hobbylobby.Repos

import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.User

class ProfileRepository {
    fun getProfile1(Id: Int): User {
        return User(
            id = Id,
            name = "Pedro",
            image = "https://www.google.com",
            description = "I am a student at UVG",
            socialMedia = listOf(
                SocialMedia(
                    name = "Facebook",
                    url = "https://www.facebook.com",
                    image = "https://www.google.com"
                ),
                SocialMedia(
                    name = "Instagram",
                    url = "https://www.instagram.com",
                    image = "https://www.google.com"
                ),
                SocialMedia(
                    name = "Twitter",
                    url = "https://www.twitter.com",
                    image = "https://www.google.com"
                )
            )
        )

    }

    fun getProfile2(Id: Int): User {
        return User(
            id = Id,
            name = "Oscar",
            image = "https://www.google.com",
            description = "I like pokemon",
            socialMedia = listOf(
                SocialMedia(
                    name = "Facebook",
                    url = "https://www.facebook.com",
                    image = "https://www.google.com"
                ),
                SocialMedia(
                    name = "Snapchat",
                    url = "https://www.snapchat.com",
                    image = "https://www.google.com"
                ),
                SocialMedia(
                    name = "Twitter",
                    url = "https://www.twitter.com",
                    image = "https://www.google.com"
                )
            )
        )

    }

    fun onsSocialAdd(social: SocialMedia) {
        // Add social media to user
    }
}