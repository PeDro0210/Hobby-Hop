package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.repository.AdminRepo
import com.pedro0210.hobbylobby.presentation.model.Request
import com.pedro0210.hobbylobby.presentation.model.RoomMember
import com.pedro0210.hobbylobby.presentation.state.AcceptanceScreenState
import com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff.AdminViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AceptacionesViewModel(
    private val repo: AdminRepo,
    private val id:String,
    description: String,
    image:String,
    name:String
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AcceptanceScreenState(
            name = name,
            image = image,
            description = description,
            requests = emptyList()
        )
    )

    val uiState = _uiState.asStateFlow()

    private lateinit var users : StateFlow<List<RoomMember>>

    init {
        viewModelScope.launch {
            users = repo.getUsersToAccept(id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
            launch {
                users.collect{requests ->
                    _uiState.update {
                        it.copy(
                            requests = requests
                        )
                    }

                }
            }
        }
    }

    fun acceptRequest(userId: String) {

        viewModelScope.launch {
            popFromRequests(userId)
            repo.addUserToCommunity(
               userId = userId,
               roomId = id
            )
        }


    }

    fun rejectRequest(userId: String) {
        viewModelScope.launch {
            popFromRequests(userId)
            repo.denyUserToCommunity(
                userId = userId,
                roomId = id
            )
        }

    }

    private fun popFromRequests(id: String) {
        // Get the current list of requests
        val newRequests = uiState.value.requests.filter { it.id != id }

        _uiState.update {
            it.copy(
                requests = newRequests
            )
        }
    }

    companion object {
        fun provideFactory(
            id:String,
            image:String,
            description:String,
            name:String,
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repo = AdminRepo()
                AceptacionesViewModel(
                    id = id,
                    image = image,
                    repo = repo,
                    name = name,
                    description = description
                )
            }
        }
    }
}
