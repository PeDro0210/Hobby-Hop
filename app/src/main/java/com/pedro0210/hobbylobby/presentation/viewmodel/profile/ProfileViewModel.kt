package com.pedro0210.hobbylobby.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.presentation.model.SocialMedia
import com.pedro0210.hobbylobby.presentation.state.ProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val preferences: UserPreferences
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private fun onLoadUser(ID: Int) {
        viewModelScope.launch {
            val CscreenState = _state.value

            _state.update { it.copy(
                user = CscreenState.user?.copy(
                    id = ID
                ),  // TODO: Change this to repository call
                isLoading = true,
                hasError = false
            )}

            delay(2000)

            if (!_state.value.hasError){
                _state.update { it.copy(
                    isLoading = false,
                    isSuccess = true,
                    hasError = false
                )}

            }


        }
    }

    private fun onLogoutClick() { //TODO: Change this to
        viewModelScope.launch {
            preferences.logOut()
        }
    }

    private fun onSocialAdd(social: SocialMedia) {
        viewModelScope.launch {
            _state.update { it.copy(
                user = _state.value.user?.socialMedia?.plus(social)?.let { it1 ->
                    _state.value.user?.copy(
                        socialMedia = it1
                    )
                }
            )}
        }
    }

}