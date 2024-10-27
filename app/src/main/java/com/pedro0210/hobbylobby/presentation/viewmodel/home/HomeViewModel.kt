package com.pedro0210.hobbylobby.presentation.viewmodel.home

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    //TODO: add the repo here
) {
    //place holder
    private val _countries = MutableStateFlow<List<Community>>(emptyList())
    private val _ownCommunities = MutableStateFlow<List<Community>>(emptyList())

    private val _state = MutableStateFlow(
        HomeScreenState(
            countries = _countries.value,
            ownCommunities = _ownCommunities.value
        )
    )

    val state = _state.asStateFlow()

    //TODO: change the place holder
    val countries: StateFlow<List<Community>> = _countries.asStateFlow()
    val ownCommunities: StateFlow<List<Community>> = _ownCommunities.asStateFlow()

    //TODO: add this line for the stateIn, for the repo with both countries and own communities
    //characterRepo.getAll()
    //  .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private fun getCountries() {
        //TODO: call the repo with the firebase implementation
    }

    private fun getOwnCommunities() {
       //TODO: call the repo with the firebase implementation
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            HomeViewModel()
        }
    }
}