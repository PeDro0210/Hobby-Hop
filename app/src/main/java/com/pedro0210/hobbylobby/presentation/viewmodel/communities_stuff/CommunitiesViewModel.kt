package com.pedro0210.hobbylobby.presentation.viewmodel.communities_stuff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
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
    title: String,
    description: String,
    image: String,
    communityType: CommunityType,
    partOfCommunity: Boolean
): ViewModel(){


    private val _state = MutableStateFlow(
        ComunitiesScreenState(
            communities = emptyList(),
            title = title,
            description = description,
            partOfCommunity = false,
            id = id,
            image = ""
        )
    )

    val state = _state.asStateFlow()

    private lateinit var communities : StateFlow<List<Community>>

    init {
        viewModelScope.launch {

            communities = repo.getCommunities(communityType, id)
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
            println("Communitytype : $communityType")
            println("Communities: $communities")
            launch{
                communities.collect{ communitiesList ->
                    println("CommunitiesViewModel: $communitiesList")
                    _state.update {
                        it.copy(
                            communities = communitiesList,
                            isLoading = false
                        )
                    }
                }
            }

            launch{
                _state.update{
                    it.copy(
                        title = title,
                        description = description,
                        partOfCommunity = partOfCommunity,
                        id = id,
                        image = image,
                    )
                }
            }

        }
    }


    companion object{
        fun provideFactory(
            repo: CommunitiesRepo,
            communityType: CommunityType,
            id: String,
            title: String,
            description: String,
            image: String,
            partOfCommunity: Boolean

        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {

                CommunitiesViewModel(
                    communityType = communityType,
                    repo = repo,
                    id = id,
                    title = title,
                    description = description,
                    image = image,
                    partOfCommunity = partOfCommunity
                )
            }
        }
    }
}