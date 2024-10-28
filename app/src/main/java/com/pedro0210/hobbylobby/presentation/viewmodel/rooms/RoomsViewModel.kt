package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.R
import com.pedro0210.hobbylobby.presentation.model.RoomMember


class RoomsViewModel(
    id: String
) : ViewModel() {

    //TODO: there's not fucking state lmao
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
        // TODO: Handle join room action
    }

    companion object {
        fun provideFactory(id: String) : ViewModelProvider.Factory = viewModelFactory {
           initializer {
               RoomsViewModel(id)
           }
        }
    }
}

