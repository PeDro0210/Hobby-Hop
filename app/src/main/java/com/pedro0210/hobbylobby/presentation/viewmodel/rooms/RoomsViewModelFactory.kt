package com.pedro0210.hobbylobby.presentation.viewmodel.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoomsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}