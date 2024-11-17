package com.pedro0210.hobbylobby.presentation.viewmodel.profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.repository.UserRepo
import com.pedro0210.hobbylobby.presentation.event.UserEvent
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.model.User
import com.pedro0210.hobbylobby.presentation.state.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepo,
    id: String,
    name: String,
    pfp: String
): ViewModel() {
    private val _state = MutableStateFlow(UserState(
        user = User(
            id = id,
            name = name,
            image = pfp,
            description = "",
            socialMedia = emptyList()
        )
    ))

    val state = _state.asStateFlow()

    private lateinit var description: StateFlow<String>
    private lateinit var socialMedia: StateFlow<List<SocialMedia>>

    init {
        viewModelScope.launch {

            description = repository.getDescription(id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

            socialMedia = repository.getSocials(id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            launch {
                description.collect { description ->
                    _state.update {
                        it.copy(
                            user = it.user.copy(
                                description = description
                            )
                        )
                    }
                }
            }


            launch {
                socialMedia.collect { socialMedia ->
                    println("Socials"+socialMedia)
                    _state.update {
                        it.copy(
                            user = it.user.copy(
                                socialMedia = socialMedia
                            )
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            id: String,
            name: String,
            pfp: String
        ) : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel(
                    repository = UserRepo(),
                    id = id,
                    name = name,
                    pfp = pfp
                )
            }
        }
    }
}