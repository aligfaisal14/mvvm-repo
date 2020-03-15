package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }

}