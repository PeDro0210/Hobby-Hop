package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pedro0210.hobbylobby.data.datastore.UserPreferences
import com.pedro0210.hobbylobby.data.repository.CommunitiesRepo
import com.pedro0210.hobbylobby.presentation.model.Community
import com.pedro0210.hobbylobby.presentation.model.CommunityType
import com.pedro0210.hobbylobby.presentation.state.ComunitiesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunitiesViewModel(
    repo: CommunitiesRepo,
    id: String,
    communityType: CommunityType,

): ViewModel(){


    private val _state = MutableStateFlow(
        ComunitiesScreenState(
            communities = emptyList(),
            title = "",
            description = "",
            partOfCommunity = false,
            id = id,
            image = ""
        )
    )

    val state = _state.asStateFlow()

    private lateinit var communities : StateFlow<List<Community>>
    private lateinit var community: StateFlow<Community>

    init {
        viewModelScope.launch {
            communities = repo.getCommunities(communityType, id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

            community = repo.getCommunity(id, communityType)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), defaultCommunity())

            launch{
                communities.collect{ communitiesList ->
                    _state.update {
                        it.copy(
                            communities = communitiesList,
                        )
                    }
                }
            }

            launch{
                community.collect{community->
                    _state.update{
                        it.copy(
                            title = community.title,
                            description = community.description,
                            partOfCommunity = community.partOfCommunity,
                            id = community.id,
                            image = community.image,
                        )
                    }
                }
            }

        }
    }
    private fun defaultCommunity():Community{
        return Community(
            title = "",
            description = "",
            image = "",
            partOfCommunity = false,
            id = "",
            type = CommunityType.bigCommunity
        )
    }
    companion object{
        fun provideFactory(
            id: String,
            communityType: CommunityType,

        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repo = CommunitiesRepo()
                CommunitiesViewModel(
                    repo,
                    id,
                    communityType,
                )
            }
        }
    }
}