package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import android.view.View
import androidx.lifecycle.ViewModel
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository
import com.riyadhfoods1.mvvmsampleapp.util.ApiException
import com.riyadhfoods1.mvvmsampleapp.util.Coroutines

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    var email:String?=null
    var password:String?=null

    var authListner:AuthListner?=null

    fun getLoggedInuser() = userRepository.getUser()


    fun onLoginButtonclicked(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFaliure("Invalid email or password")
            return
        }

        //success

//        var loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListner?.onSuccess(loginResponse)


        Coroutines.main {
            ///222222
            try{
                var authResponse = userRepository.userLogin(email!!, password!!)
                authResponse.user?.let {
                   authListner?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListner?.onFaliure(authResponse.message!!)
            }catch (e:ApiException){
                authListner?.onFaliure(e.message!!)
            }



//11111
//            var loginResponse = UserRepository().userLogin(email!!, password!!)
//            if(loginResponse.isSuccessful){
//                authListner?.onSuccess(loginResponse.body()?.user!!)
//            }else{
//                authListner?.onFaliure("Error code ${loginResponse.code()}")
//            }
        }



    }
}