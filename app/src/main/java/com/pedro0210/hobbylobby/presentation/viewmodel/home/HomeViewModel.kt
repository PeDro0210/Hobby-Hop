package com.pedro0210.hobbylobby.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.repository.HomeRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class HomeViewModel(
    repo: HomeRepo,
    userData: UserData
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeScreenState(
            countries = emptyList(),
            ownCommunities = emptyList()
        )
    )

    val state = _state.asStateFlow()

    //will be initialize on the init
    private lateinit var countries: StateFlow<List<Community>>
    private lateinit var ownCommunities: StateFlow<List<Community>>

    init {
        viewModelScope.launch {

            userData.loggedIn()
            countries = repo.getCountries()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            ownCommunities = repo.getOwnRooms()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            //have to do it async for each of the flows
            launch {
                countries.collect { countriesList ->
                    _state.update { it.copy(countries = countriesList) }
                }
            }

            launch {
                ownCommunities.collect { ownCommunitiesList ->
                    _state.update { it.copy(ownCommunities = ownCommunitiesList) }
                }
            }
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val repo = HomeRepo(userPreferences = UserData(application.dataStore))
                val userData = UserData(application.dataStore)
                HomeViewModel(
                    repo = repo,
                    userData = userData
                )
            }
        }
    }
}