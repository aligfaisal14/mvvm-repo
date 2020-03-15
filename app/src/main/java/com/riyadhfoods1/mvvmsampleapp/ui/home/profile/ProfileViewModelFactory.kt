package com.riyadhfoods1.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userRepository) as T
    }

}