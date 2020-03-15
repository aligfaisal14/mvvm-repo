package com.riyadhfoods1.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository

class ProfileViewModel (userRepository: UserRepository): ViewModel() {
    val user = userRepository.getUser()

}
