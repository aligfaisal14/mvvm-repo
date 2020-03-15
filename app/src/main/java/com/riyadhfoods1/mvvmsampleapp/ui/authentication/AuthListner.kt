package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import androidx.lifecycle.LiveData
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity

interface AuthListner {
    fun onStarted()
    fun onSuccess(userEntity: UserEntity)
    fun onFaliure(message:String)
}