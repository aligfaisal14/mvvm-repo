package com.riyadhfoods1.mvvmsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.riyadhfoods1.mvvmsampleapp.data.database.AppDatabase
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.Quote
import com.riyadhfoods1.mvvmsampleapp.data.network.MyApi
import com.riyadhfoods1.mvvmsampleapp.data.network.SafeApiRequest
import com.riyadhfoods1.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository (private val api: MyApi, private val db:AppDatabase): SafeApiRequest() {
private val quotes = MutableLiveData<List<Quote>>()


    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes(){
        if(isFetchNeeded()){
            val reponse = apiRequest { api.getQuotes() }
            quotes.postValue(reponse.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }


    private fun saveQuotes(quotes: List<Quote>) {
  //////Seprate Thread from main created in Coroutine
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }

}