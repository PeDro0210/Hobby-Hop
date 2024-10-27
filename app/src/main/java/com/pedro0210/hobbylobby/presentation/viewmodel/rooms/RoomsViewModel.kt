package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.model.RoomMember


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

