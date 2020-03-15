package com.riyadhfoods1.mvvmsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riyadhfoods1.mvvmsampleapp.data.database.AppDatabase
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity
import com.riyadhfoods1.mvvmsampleapp.data.network.MyApi
import com.riyadhfoods1.mvvmsampleapp.data.network.SafeApiRequest
import com.riyadhfoods1.mvvmsampleapp.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(var api:MyApi,var database:AppDatabase):SafeApiRequest() {


   suspend fun userLogin(email:String,password:String):AuthResponse{

       //1111111
//        var loginResponse = MutableLiveData<String>()
//
//
//        MyApi().userLogin(email,password).enqueue(object : Callback<ResponseBody>{
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//            loginResponse.value=t.message
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//            if(response.isSuccessful){
//                loginResponse.value = response.body()?.string()
//            }else{
//                loginResponse.value = response.errorBody()?.string()
//            }
//            }
//
//        })
//        return loginResponse

       ////2222222

//        return MyApi().userLogin(email,password)

       //3333333
       return apiRequest { api.userLogin(email,password) }


    }

    suspend fun saveUser(user:UserEntity) = database.getUserDao().upsert(user)

    fun getUser() = database.getUserDao().getUser()
}