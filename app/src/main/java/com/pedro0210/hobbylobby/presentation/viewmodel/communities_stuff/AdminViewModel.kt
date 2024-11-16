package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserData
import com.pedro0210.hobbylobby.data.repository.CommunitiesRepo
import com.pedro0210.hobbylobby.dataStore
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.state.AdminCommunitiesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminViewModel(
    repo: CommunitiesRepo,
    communityType: CommunityType,
    userData: UserData,
    id: String = ""
): ViewModel(){


    private val _state = MutableStateFlow(
       AdminCommunitiesState(
            communities = emptyList(),
            type = communityType
       )
    )

    val state = _state.asStateFlow()

    private lateinit var communities : StateFlow<List<Community>>

    init {
        viewModelScope.launch {

           val idAdmin:String = if (id == ""){
                userData.getId().first()
            } else{
                id
            }

            communities = repo.getAdminCommunities(communityType, idAdmin)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
            launch{
                communities.collect{ communitiesList ->
                    println("CommunitiesViewModel: $communitiesList")
                    _state.update {
                        it.copy(
                            communities = communitiesList,
                        )
                    }
                }
            }


        }
    }


    companion object{
        fun provideFactory(
            repo: CommunitiesRepo,
            communityType: CommunityType,
            id: String = ""

        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val userData = UserData(application.dataStore)
                AdminViewModel(
                    communityType = communityType,
                    repo = repo,
                    userData = userData,
                    id = id
                )
            }
        }
    }
}