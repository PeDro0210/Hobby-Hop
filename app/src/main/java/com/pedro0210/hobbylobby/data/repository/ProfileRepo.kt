package com.pedro0210.hobbylobby.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.pedro0210.hobbylobby.presentation.model.Profile
import com.pedro0210.hobbylobby.presentation.model.SocialMediaCreation
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class ProfileRepo {

    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()


    suspend fun changeUserInfoWImage(userId: String, username: String, bio: String, image: Uri): String{
        return try {
            firestore.collection("users").document(userId).update("bio", bio)
            firestore.collection("users").document(userId).update("username", username)
            val userImageRef = storage.reference.child("users/$userId/pfp.png")
            userImageRef.putFile(image!!).await()
            val imageUrl = userImageRef.downloadUrl.await()
            firestore.collection("users").document(userId).update("pfp", imageUrl)
            return "Success"
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }

    }

    suspend fun changeUserInfoWOImage(userId: String, username: String, bio: String): String{
        return try {
            firestore.collection("users").document(userId).update("bio", bio)
            firestore.collection("users").document(userId).update("username", username)
            return "Success"
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }
    }

    suspend fun addSocialMedia(userId: String, socialmedia: List<SocialMediaCreation>):String{
        return try {
            socialmedia.forEach {
                val savebleName = it.name.lowercase()
                val socialMediaRef =
                    storage.reference.child("users/$userId/social_media/$savebleName/pfp.png")
                socialMediaRef.putFile(it.image!!).await()
                val imageUrl = socialMediaRef.downloadUrl.await()
                val socialMedia = mapOf(
                    "name" to it.name,
                    "link" to it.url,
                    "pfp" to imageUrl
                )
                firestore.collection("users").document(userId).collection("socials").add(socialMedia)
            }

            return "Success"
        }catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }
    }

    suspend fun getUser(userId: String): Profile{
        return try {
            val user = firestore.collection("users").document(userId).get().await()
            val name = user.get("username").toString()
            val bio = user.get("bio").toString()
            val image = user.get("pfp").toString()
            val socials = firestore.collection("users").document(userId).collection("socials").get().await()
            val socialMedia = mutableListOf<SocialMediaCreation>()
            socials.forEach {
                socialMedia.add(
                    SocialMediaCreation(
                        name = it.get("name").toString(),
                        url = it.get("link").toString(),
                        imageUrl = it.get("pfp").toString(),
                        image = null,
                        new = false
                    )
                )
            }
            Profile(
                id = userId,
                name = name,
                description = bio,
                image = null,
                imageUrl = image,
                socialMedia = socialMedia
            )
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            Profile(
                id = "Error",
                name = "Error",
                description = "Error",
                image = null,
                socialMedia = listOf(),
                imageUrl = "Error"
            )
        } catch (e: Exception) {
            println("Error: $e")
            Profile(
                id = "Error",
                name = "Error",
                description = "Error",
                image = null,
                socialMedia = listOf(),
                imageUrl = "Error"
            )
        }
    }

    suspend fun deleteSocialMedia(userId: String, socialMediaName: String): String{
        return try {
            val saveableName = socialMediaName.lowercase()
            val socialMediaRef = storage.reference.child("users/$userId/social_media/$saveableName/pfp.png")
            socialMediaRef.delete().await()
            val socialMedia = firestore.collection("users").document(userId).collection("socials").whereEqualTo("name", socialMediaName).get().await()
            val socialMediaId = socialMedia.documents.firstOrNull()?.id
            if (socialMediaId != null) {
                firestore.collection("users").document(userId).collection("socials").document(socialMediaId).delete().await()
                return "Success"
            } else {
                return "Error"
            }
        } catch (e: CancellationException) {
            println("Corrutine was cancelled: $e")
            return "Error"
        } catch (e: Exception) {
            println("Error: $e")
            return "Error"
        }
    }

}