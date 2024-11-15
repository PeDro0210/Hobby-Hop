package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro0210.hobbylobby.data.repository.CreatorRepo
import com.pedro0210.hobbylobby.presentation.event.CreatorEvent
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.state.CreatorScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatorViewModel(
    private val repo: CreatorRepo
) : ViewModel() {

    fun onEvent(Event: CreatorEvent) {
        when (Event) {
            is CreatorEvent.onComunityCreate -> {
                oncomunityCreate(Event.title, Event.description, Event.image, Event.rooms)
            }
            is CreatorEvent.onRoomCreate -> {

        }
    }

    private val _state = MutableStateFlow(CreatorScreenState())
    val state = _state.asStateFlow()

    private fun oncomunityCreate(title: String, description: String, image: String, rooms: List<Community>) {
        viewModelScope.launch {
            repo.createCommunity(title, description, image, rooms)
        }
    }



}