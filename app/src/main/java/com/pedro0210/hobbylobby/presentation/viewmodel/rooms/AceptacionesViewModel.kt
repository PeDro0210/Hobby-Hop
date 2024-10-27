package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedro0210.hobbylobby.presentation.model.Request


class AceptacionesViewModel : ViewModel() {

    private val _requests = MutableLiveData<List<Request>>()
    val requests: LiveData<List<Request>> get() = _requests

    init {
        // Initialize with some dummy data
        _requests.value = listOf(
            Request("Juan"),
            Request("Anna"),
            Request("Marcos"),
            Request("Luis"),
            Request("Carlos")
        )
    }

    fun acceptRequest(userName: String) {
        // TODO Handle accept request action
    }

    fun rejectRequest(userName: String) {
        // TODO Handle reject request action
    }
}
