package com.riyadhfoods1.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.riyadhfoods1.mvvmsampleapp.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor( context: Context) : Interceptor {
    private val context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
      if(!isNetworkAvailable()){
      throw NoInternetException("Make sure you have active data internet connection")
      }
        else{
          return chain.proceed(chain.request())
      }
    }


    private fun isNetworkAvailable():Boolean{
     val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }
    }

}