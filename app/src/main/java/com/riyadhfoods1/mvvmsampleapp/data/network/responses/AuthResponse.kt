package com.riyadhfoods1.mvvmsampleapp.data.network.responses

import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity

data class AuthResponse (var isSuccessful:Boolean?,val message:String?,var user: UserEntity?)