package com.pedro0210.hobbylobby.Screens.Rooms.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedro0210.hobbylobby.R

data class RoomMember(
    val name: String,
    val status: String,
    val avatarResource: Int
)

class RoomsViewModel : ViewModel() {

    private val _members = MutableLiveData<List<RoomMember>>()
    val members: LiveData<List<RoomMember>> get() = _members

    init {
        // Initialize with some dummy data
        _members.value = listOf(
            RoomMember("Juan", "Conectado", R.drawable.avatar),
            RoomMember("Abby", "18 min", R.drawable.avatar),
            RoomMember("Oscar", "Me gusta Pokémon", R.drawable.avatar),
            RoomMember("Name", "18 min", R.drawable.avatar),
            RoomMember("Name", "18 min", R.drawable.avatar)
        )
    }

    fun joinRoom() {
        // TODO Handle join room action
    }
}

data class Request(
    val userName: String
)

class AceptacionesViewModel : ViewModel() {

    private val _requests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> get() = _requests

    init {
        // Initialize with some dummy data
        _requests.value = listOf(
            Request("Juan"),
            Request("Anna"),
            Request("Marcos"),
            Request("Luis"),
            Request("Carlos")
        )
    }

    fun acceptRequest(userName: String) {
        // TODO Handle accept request action
    }

    fun rejectRequest(userName: String) {
        // TODO Handle reject request action
    }
}