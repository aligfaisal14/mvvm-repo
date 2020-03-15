package com.riyadhfoods1.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor( context: Context) : Interceptor {
    private val context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun isNetworkAvailable():Boolean{
     val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }
    }

}