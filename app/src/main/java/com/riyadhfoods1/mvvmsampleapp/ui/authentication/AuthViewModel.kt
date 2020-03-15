package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.riyadhfoods1.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository
import com.riyadhfoods1.mvvmsampleapp.util.ApiException
import com.riyadhfoods1.mvvmsampleapp.util.Coroutines
import com.riyadhfoods1.mvvmsampleapp.util.NoInternetException

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    var email:String?=null
    var password:String?=null
    var name:String?=null
    var passwordConfirm:String?=null

    var authListner:AuthListner?=null

    fun getLoggedInuser() = userRepository.getUser()

    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }

    }


    fun gotoLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


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
            }catch (e:NoInternetException){
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


    fun onSignUpButton(view: View) {
        authListner?.onStarted()
        if (name.isNullOrEmpty()) {
            authListner?.onFaliure("Name is required")
            return
        }
        if(email.isNullOrEmpty()){
            authListner?.onFaliure("Email is required")
            return
        }
        if(password.isNullOrEmpty()){
            authListner?.onFaliure("Please enter the password")
            return
        }
        if(password == passwordConfirm){
            authListner?.onFaliure("Password dose not match")
            return
        }

        Coroutines.main {
            ///222222
            try{
                var authResponse = userRepository.userSignUp(name!!,email!!, password!!)
                authResponse.user?.let {
                    authListner?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListner?.onFaliure(authResponse.message!!)
            }catch (e:ApiException){
                authListner?.onFaliure(e.message!!)
            }catch (e:NoInternetException){
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